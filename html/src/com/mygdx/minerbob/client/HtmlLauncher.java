package com.mygdx.minerbob.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(480, 320);
        }

        @Override
        public ApplicationListener createApplicationListener () {
           //     return new MainGame(this);
                return new ApplicationListener() {
                        @Override
                        public void create() {

                        }

                        @Override
                        public void resize(int width, int height) {

                        }

                        @Override
                        public void render() {

                        }

                        @Override
                        public void pause() {

                        }

                        @Override
                        public void resume() {

                        }

                        @Override
                        public void dispose() {

                        }
                };
        }
}