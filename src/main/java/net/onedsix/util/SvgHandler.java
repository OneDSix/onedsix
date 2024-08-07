package net.onedsix.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.github.weisj.jsvg.SVGDocument;
import com.github.weisj.jsvg.geometry.size.FloatSize;
import com.github.weisj.jsvg.parser.SVGLoader;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Slf4j
public class SvgHandler {
    public static final SVGLoader loader = new SVGLoader();

    /**
     * Takes in a path to an internal file containing an SVG-formatted document.<br>
     * Assumes the file is in the {@code assets} directory.
     * */
    public static SVGDocument loadSvgFromFile(String path) {
        InputStream svgUrl = null;
        try {
            assert path.endsWith(".svg");
            svgUrl = Gdx.files.internal(path).read();
            return loader.load(svgUrl);
        }
        catch (GdxRuntimeException gdxre) {
			log.error(gdxre.getMessage(), gdxre);
            return null;
        }
        finally {
            assert svgUrl != null;
            try {
                svgUrl.close();
            }
            catch (IOException ioe) {
                log.error(ioe.getMessage(), ioe);
            }
        }
    }

    public static SVGDocument loadSvgFromString(String svg) {
        return loader.load(new ByteArrayInputStream(svg.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * Stores a compiled SVG document as a PNG in the temp directory.<br>
     * For use in all SVG related code.<br>
     * This code is very weirdly written, I recommend not touching it.
     * */
    public static String svgToPng(SVGDocument svg, String name) {
        Graphics2D g = null;
        try {
            // Get needed values
            FloatSize size = svg.size();
            String fileName = "./temp/svg/" + name + ".png";

            // Get the image to write
            BufferedImage bi = new BufferedImage((int) size.width, (int) size.height, 2);

            // Get the graphics context of the buffered image
            g = bi.createGraphics();

            // Render the SVG onto the buffered image
            svg.render(null, g);

            // Save the buffered image as PNG
            File outputfile = new File(fileName);
            ImageIO.write(bi, "png", outputfile);
			log.info("Saved temporary SVG to " + fileName);
            return fileName;
        }
        catch (Exception e) { // IOException, NullPointerException
			log.error(e.getMessage(), e);
            return "assets/error.png";
        }
        finally {
            if (g != null)
            	g.dispose();
        }
    }
}
