package model;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;


import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.awt.Image;


public class ManipulandoImagem {

	public static byte[] getImgBytes(String caminho) {
		BufferedImage imagem;
		byte[] byteArray = null;

		if(caminho != "")
		{
			try {		
				imagem = ImageIO.read(new File(caminho));
				ByteArrayOutputStream bytesImg = new ByteArrayOutputStream();
				ImageIO.write((BufferedImage)imagem, "jpg", bytesImg);
				bytesImg.flush();
				byteArray = bytesImg.toByteArray();
				bytesImg.close();

			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}

		return byteArray;

	}

	public static String exibiImagemLabel(byte[] minhaimagem)
	{
		Image image = null;
		if(minhaimagem!=null)
		{
			InputStream input = new ByteArrayInputStream(minhaimagem);
			try {
				BufferedImage imagem = ImageIO.read(input);
				ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
				ImageIO.write(imagem, "png", baos);
				String encodedImage = Base64.encode(baos.toByteArray());
				return encodedImage;
			} catch (IOException ex) {
			}

			return image.toString();
		}

		return null;

	}
}
