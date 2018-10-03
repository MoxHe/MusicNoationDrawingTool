package marlin.Reaction;

import marlin.I;
import marlin.UC;
import marlin.graphicsLib.G;

import java.awt.*;
import java.util.ArrayList;

public class Shape {
    public String name;
    public Prototype.List prototypes = new Prototype.List();
    public Shape(String name){this.name = name;}


    public static class Prototype extends Ink.Norm{
        public int nBlends = 1;//running average of the points for this shape
        public void blend(Ink.Norm norm){
            for(int i =0; i<N;i++){
                points[i].blend(norm.points[i], nBlends);
            }
            nBlends++;
        }
        public static class List extends ArrayList<Prototype> implements I.Show{
            public static Prototype bestMatch;
            public int bestDist(Ink.Norm norm){
                bestMatch = null;
                int bestSoFar = UC.noMatchDist;
                for (Prototype p : this) {
                    int d = p.dist(norm);
                    if(d<bestSoFar){bestSoFar = d; bestMatch = p;}
                }
                return bestSoFar;
            }
            private static int m = 10, w = 60;
            private static G.VS showBox = new G.VS(m,m,w,w);
            @Override
            public void show(Graphics g) {
                g.setColor(Color.ORANGE);
                for(int i =0;i < size();i++){
                    int x = (i+1)*m + i*w;
                    showBox.set(x, m, w, w);
                    Prototype p = get(i);
                    p.drawAt(g,showBox);
                    g.drawString("" + p.nBlends, x, m);
                }

            }
        }


    }
}
