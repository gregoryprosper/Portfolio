using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BlockBuilder
{
    class Block
    {
        private Rectangle bounds;
        public Rectangle Bounds
        {
            get{return bounds;}
        }
        private Color color;
        public Color Color
        {
            get{return color;}
        }
        public static int size = 20;

        public Block()
        {
            this.bounds = new Rectangle(new Point(size, size), new Size(size, size));
            this.color = Color.Black;
        }

        public Block(int x, int y, Color color)
        {
            this.bounds = new Rectangle(new Point(x, y), new Size(size, size));
            this.color = color;
        }

        public void move(int x, int y, Rectangle enclosedBounds)
        {
            if(bounds.X + bounds.Width + x <= enclosedBounds.Width && bounds.X + x >= enclosedBounds.X){
                bounds.X += x;
            }

            if (bounds.Y + bounds.Height + y <= enclosedBounds.Height && bounds.Y + y >= enclosedBounds.Y)
            {
                bounds.Y += y;
            }
        }

        public bool containsPoint(Point point)
        {
            if (point.X >= bounds.X && point.X <= bounds.X + bounds.Width && point.Y >= bounds.Y && point.Y <= bounds.Y + bounds.Height)
            {
                return true;
            }
            else return false;
        }
    }
}
