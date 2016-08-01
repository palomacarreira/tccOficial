package security;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.io.StringReader;

public class Base64 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7415625217821487282L;

	// Tabela de caracteres v�lidos na base 64
	private static final byte[] base64Chars = {
			'A','B','C','D','E','F','G','H',
			'I','J','K','L','M','N','O','P',
			'Q','R','S','T','U','V','W','X',
			'Y','Z','a','b','c','d','e','f',
			'g','h','i','j','k','l','m','n',
			'o','p','q','r','s','t','u','v',
			'w','x','y','z','0','1','2','3',
			'4','5','6','7','8','9','+','/',
		};

	// Tabela reversa da base 64
	private static final byte[] reverseBase64Chars = new byte[0x100];


	static {
		// Preenche todas as posi��es do vetor com caracteres n�o v�lidos na base 64
		for ( int i=0; i < reverseBase64Chars.length; i++ )
			reverseBase64Chars[i] = -1;

		// Ajusta a tabela reversa da base 64
		for ( int i=0; i < base64Chars.length; i++ )
			reverseBase64Chars[ base64Chars[i] ] = (byte) i;
	}

	/**
	 * Codifica uma sequ�ncia de bytes para a base 64.
	 *
	 * @param in Sequ�ncia de bytes para codificar.
	 * @return Texto na base 64.
	 */
	public static String encode( byte[] in ) {
		byte u, v, w, x;

		int c, d, e, k = 0, end = 0;

		StringBuffer out = new StringBuffer();
		ByteArrayInputStream inputStream = new ByteArrayInputStream( in );

		while ( end == 0 ) {
			if ( (c = inputStream.read()) == -1 ) {
				c = 0; end = 1;
			}
			if ( (d = inputStream.read()) == -1 ) {
				d = 0; end += 1;
			}
			if ( (e = inputStream.read()) == -1 ) {
				e = 0; end += 1;
			}

			u = base64Chars[ c >> 2 ];
			v = base64Chars[ ( 0x00000003 & c ) << 4 | d >> 4 ];
			w = base64Chars[ ( 0x0000000F & d ) << 2 | e >> 6 ];
			x = base64Chars[ e & 0x0000003F ];

			if ( k == 76 ) k = 0;
			if ( end >= 1 ) x = (byte) '=';
			if ( end == 2 ) w = (byte) '=';

			if ( end < 3 ) {
				out.append("" + (char)u + (char)v + (char)w + (char)x);
			}

			k += 4;
		}

		return out.toString();
	}

	/**
	 * Decodifica um texto na base 64 para seu formato original. 
	 * 
	 * @param in String na base 64 para ser decodificada.
	 * @return Sequ�ncia de bytes decodificados.
	 */
	public static byte[] decode( String in ) {
		int c = 0, d = 0, e = 0, f = 0, i = 0, n = 0;

		ByteArrayOutputStream out = new ByteArrayOutputStream( in.length() );
		StringReader reader = new StringReader( in ); 		

		try {
			do {
				f = reader.read();

				if ( f >= 0 && f < 128 && (i = reverseBase64Chars[f]) != -1 ) {
					if ( n % 4 == 0 ) {
						c = i << 2;
					}
					else if ( n % 4 == 1 ) {
						c = c | ( i >> 4 );
						d = ( i & 0x0000000f ) << 4;
					}
					else if ( n % 4 == 2 ) {
						d = d | ( i >> 2 );
						e = ( i & 0x00000003 ) << 6;
					}
					else {
						e = e | i;
					}
	
					n++;
	
					if ( n % 4 == 0 ) {
						out.write((char)c);
						out.write((char)d);
						out.write((char)e);
					}
				}
			} while ( f != -1 );

			if ( n % 4 == 3 ) {
				out.write((char)c);
				out.write((char)d);
			}
			else if ( n % 4 == 2 ) {
				out.write((char)c);
			}
		}
		catch ( Exception ex ) {
			throw new RuntimeException( ex );
		}

		return out.toByteArray();
	}

}
