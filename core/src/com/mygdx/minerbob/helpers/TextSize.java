package com.mygdx.minerbob.helpers;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

/**
 * Created by Алексей on 29.09.2017.
 */

public class TextSize {
    private static GlyphLayout glyphLayout;

    public static void load() {
        glyphLayout = new GlyphLayout();
    }

    public static float getWidth(BitmapFont font, String text) {
        glyphLayout.setText(font, text);
        return glyphLayout.width;
    }

    public static float getHeight(BitmapFont font, String text) {
        glyphLayout.setText(font, text);
        return -glyphLayout.height;
    }
}
