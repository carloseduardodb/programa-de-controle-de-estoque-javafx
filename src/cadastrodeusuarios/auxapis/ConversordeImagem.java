/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastrodeusuarios.auxapis;

import java.awt.image.BufferedImage;

/**
 *
 * @author Carlos
 */
public class ConversordeImagem {
    public static BufferedImage scale(BufferedImage src, int w, int h)
    {
    BufferedImage img = 
            new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            int x, y;
            int ww = src.getWidth();
            int hh = src.getHeight();
            int[] ys = new int[h];
            for (y = 0; y < h; y++)
                ys[y] = y * hh / h;
            for (x = 0; x < w; x++) {
                int newX = x * ww / w;
            for (y = 0; y < h; y++) {
                int col = src.getRGB(newX, ys[y]);
            img.setRGB(x, y, col);
        }
    }
   return img;
}
}
