using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BlockBuilder
{
    [Serializable]
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
            if(bounds.X + bounds.Width + x <= enclosedBounds.Width - 2 && bounds.X + x >= 0){
                Console.WriteLine("Block Left side: " + bounds.X + " Block Right side: " + (bounds.X + bounds.Width));
                Console.WriteLine("enclosedBounds left side: " + enclosedBounds.X + " enclosedBounds right side: " + enclosedBounds.X + enclosedBounds.Width);
                bounds.X += x;
            }

            if (bounds.Y + bounds.Height + y <= enclosedBounds.Height - 2 && bounds.Y + y >= 0)
            {
                Console.WriteLine("Block Top side: " + bounds.Y + " Block Bottom side: " + (bounds.Y + bounds.Height));
                Console.WriteLine("enclosedBounds Top side: " + enclosedBounds.Y + " enclosedBounds Bottom side: " + (enclosedBounds.Y + enclosedBounds.Height));
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
