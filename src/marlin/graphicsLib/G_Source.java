//package marlin.graphicsLib;
//
//import java.awt.*;
//import java.rmi.activation.ActivationGroup_Stub;
//import java.util.Random;
//
//public class G {
//    public static Random RND = new Random();
//    public static int rnd(int max){return RND.nextInt(max);}
//    public static Color rndColor(){return new Color(rnd(256),rnd(256),rnd(256));}
//    public static void fillBackground(Graphics g, Color c){
//        g.setColor(c);
//        g.fillRect(0,0,3000,3000);
//    }
//    public static void drawCircle(Graphics g,int x, int y, int r){g.drawOval(x-r,y-r,r + r,r+r);}
//    public static class V{
//        //This is a class that matches awt.Point.
//        // It essentially is for holding a single coordinate pair (x,y).
//        // I prefer small names so it is V as in "2D Vector".
//        // Among the helpers that we eventually put into this class we will be able to add vectors component wise.
//        public static Transform T = new Transform();
//        public int x,y;
//        public V(V v){ this.x = v.x;this.y = v.y; }
//        public V(int x, int y){ this.x =x;this.y = y; }
//        public void add(V v){this.x+= v.x;this.y+=v.y;}
//        public void set(V v){this.x= v.x;this.y=v.y;}
//        public void set(int x, int y){this.x= x;this.y=y;}
//        public int tx(){return this.x * T.newScale / T.oldScale + T.dx;}
//        public int ty(){return this.y * T.newScale / T.oldScale + T.dy;}
//        public static class Transform{
//            public int dx, dy, oldScale, newScale;
//            public void setScale(int oldW, int oldH, int newW, int newH){
//                oldScale = oldW < oldH ? oldH:oldW;
//                newScale = newW < newH ? newH:newW;
//            }
//            public int trans(int oldX, int oldW, int newX, int newW){
//                return (-oldX - oldW / 2 )* newW / oldW + newX + newW / 2;
//            }
//            public void set(VS from, VS to){
//                setScale(from.size.x, from.size.y, to.size.x, to.size.y);
//                dx = trans(from.loc.x, from.size.x, to.loc.x, to.size.x);
//                dy = trans(from.loc.y, from.size.y, to.loc.y, to.size.y);
//            }
//            public void set(BBox from, VS to){
//                setScale(from.h.size(),from.v.size(),to.size.x,to.size.y);
//                dx = trans(from.h.lo, from.h.size(),to.loc.x,to.size.x);
//                dy = trans(from.v.lo, from.v.size(),to.loc.y,to.size.y);
//
//            }
//        }
//
//    }
//    public static class VS{
//        //This class represents the information you need for a rectangle.
//        // We keep it as two vectors V loc,size; We will eventually use this class for hit detection
//        // i.e. detecting if a mouse click was within the bounds of the rectangle.
//        public V loc,size;
//        //public VS(V loc, V size){this.loc = loc;this.size = size;}
//        public VS(int x,int y, int w,int h){loc = new V(x,y);size = new V(w,h);}
//        public void fill(Graphics g, Color c){ g.setColor(c);g.fillRect(loc.x,loc.y,size.x,size.y); }
//        public boolean hit(int x,int y){return loc.x <= x && loc.y<= y && x<=(loc.x+size.x) && y<=(loc.y+size.y);}
//        public void resize(int x,int y){size.x = x;size.y = y;}
//        public int lox(){return loc.x;}
//        public int midx(){return loc.x+size.x/2;}
//        public int hix(){return loc.x+size.x;}
//        public int loy(){return loc.y;}
//        public int midy(){return loc.y+size.y/2;}
//        public int hiy(){return loc.y+size.y;}
//
//
//
//    }
//    public static class LoHi{
//        //This class keeps track of two ints, one low and one high, that represent the endpoints of a range of values.
//        // These LoHi objects can be used for keeping track of the bounds on an evergrowing set of values
//        // (in other words the LoHi is expanding to include each data value as it comes in)
//        // OR they can be used as bounds to restrict values in order to force them to be within a given range.
//        // We will mostly use LoHi when we build Bounding Boxes.
//        int lo, hi;
//        public LoHi(int min, int max){ lo = min;hi = max; }
//        public void set(int v){lo = v; hi = v;}
//        public void add(int v){lo= v<lo ? v:lo; hi=v>hi?v:hi;}
//        public int size(){return lo<hi? hi - lo:1;}
//    }
//    public static class BBox{
//        //A BBox consists of two LoHi ranges, one for horizontal extents and one for vertical extents.
//        // This will eventually be useful for figuring out how large an input polyline is so that we can scale it to a standard size.
//        LoHi h,v;
//        //h:horizontal,v:vertical
//        public BBox(){h = new LoHi(0,0); v = new LoHi(0,0);}
//        public void set(int x, int y){h.set(x); v.set(y);}
//        public void add(int x, int y){h.add(x); v.add(y);}
//        public void add(V v1){h.add(v1.x);v.add(v1.y);}
//        public VS getNewVS(){return new VS(h.lo,v.lo,h.size(),v.size());}
//        public void draw(Graphics g){g.drawRect(h.lo,v.lo,h.size(),v.size());}
//    }//POLY line. LIST OF POINTS CONNECTED together by draw function
//    public static class PL{
//        //This class is used for Polylines.
//        // We could have used it for the Paths that we created in the Paint class in Day 1,
//        // but I wasn't ready to force you into using helpers.
//        public V[] points;
//        public PL(int count){
//            points = new V[count];
//            for(int i = 0; i < count; i++){
//                points[i] = new V(0,0);
//            }
//        }
//        public int size(){ return points.length; }
//        public void drawN(Graphics g, int n){
//            for (int i =1; i <n; i++){
//                g.drawLine(points[i-1].x,points[i-1].y,points[i].x,points[i].y);
//            }
//            //drawNDots(g,n);
//        }
//        public void drawNDots(Graphics g, int n){
//            for (int i = 0 ; i<n;i++){
//                drawCircle(g,points[i].x, points[i].y,4);
//            }
//        }
//        public void draw(Graphics g){ drawN(g,size());}
//        public void  transform(){
//            for (int i = 0;i<points.length;i++){
//                points[i].set(points[i].tx(),points[i].ty());
//            }
//        }
//    }
//
//}
