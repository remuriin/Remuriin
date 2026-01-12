package Practice_Swing.UI_Panels;

import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.IOException;

public class ResourceLoader
{
    public static Image loadImage(String path)
    {
        try
        {
            return ImageIO.read(ResourceLoader.class.getResource("/Resources/" + path));
        } catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
