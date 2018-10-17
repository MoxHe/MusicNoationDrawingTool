package marlin.Sandbox;

import marlin.Reaction.Gesture;
import marlin.Reaction.Ink;
import marlin.Reaction.Layer;
import marlin.Reaction.Mass;
import marlin.UC;
import marlin.graphicsLib.G;
import marlin.graphicsLib.Window;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Music1 extends Window {
    public Music1() {
        super("Music1", UC.screenWidth, UC.screenHeight);
    }

    public void paintComponent(Graphics g){
        G.fillBackground(g,Color.BLACK);
        g.setColor(Color.BLACK);
        Ink.BUFFER.show(g);
        Layer.ALL.show(g);
    }

    public void mousePressed(MouseEvent me){ Gesture.AREA.dn(me.getX(),me.getY());repaint(); }
    public void mouseDragged(MouseEvent me){ Gesture.AREA.drag(me.getX(),me.getY());repaint(); }
    public void mouseReleased(MouseEvent me){ Gesture.AREA.up(me.getX(),me.getY());repaint(); }

    public static class Music{
        public static class Sys extends Mass {
            public Sys(String layerName) {
                super(layerName);
            }

            @Override
            public void show(Graphics g) {

            }

            public static class Fmt extends Mass{

                public Fmt(String layerName) {
                    super(layerName);
                }

                @Override
                public void show(Graphics g) {

                }
            }
        }
        public static class Staff extends Mass{
            public Staff(String layerName) {
                super(layerName);
            }

            @Override
            public void show(Graphics g) {

            }

            public static class Fmt extends Mass{

                public Fmt(String layerName) {
                    super(layerName);
                }

                @Override
                public void show(Graphics g) {

                }
            }
        }
        public static class Bar extends Mass{

            public Bar(String layerName) {
                super(layerName);
            }

            @Override
            public void show(Graphics g) {

            }
        }
    }
}
