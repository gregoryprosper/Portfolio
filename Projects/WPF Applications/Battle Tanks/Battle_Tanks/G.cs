//************************************************
//
// (c) Copyright 2015 Dr. Thomas Fernandez
//
// All rights reserved.
//
//************************************************

using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Threading;

namespace BattleTanks
{
    class G
    {

        public static Canvas canvas;
        public static Random random = new Random();
        public static string ContentDir = @"..\..\Content\";

        public static double gameWidth
        {
            get
            {
                return canvas.Width;
            }
        }
        public static double gameHeight
        {
            get
            {
                return canvas.Height;
            }
        }

        static public bool checkCollision(GameObject obj1, GameObject obj2)
        {
            if (obj2.isActive && obj1.isActive)
            {
                if (Math.Abs(obj1.Y - obj2.Y) < ((obj1.ScaledHeight + obj2.ScaledHeight) / 2.0))
                {
                    if (Math.Abs(obj1.X - obj2.X) < ((obj1.ScaledWidth + obj2.ScaledWidth) / 2.0))
                    {
                        return true;
                    }
                }
            }
            return false;
        }

        public  static void CheckForAlternateContentDir(string fileName)
        {
            if (!File.Exists(G.ContentDir + fileName))
            {
                G.ContentDir = @"Content\";
            }
        }


        public static Color randColor()
        {
            return Color.FromRgb((byte)random.Next(256), (byte)random.Next(256), (byte)random.Next(256));
        }
        public static Color randDarkColor()
        {
            return Color.FromRgb((byte)random.Next(64), (byte)random.Next(64), (byte)random.Next(64));
        }

        public static Color randLightColor()
        {
            Color c;
            do
            {
                c = randColor();
            } while (!isLight(c));
            return c;
        }

        public static bool isLight(Color c)
        {
            if (c.R >= 200) return true;
            if (c.G >= 200) return true;
            if (c.B >= 200) return true;
            return false;
        }


        public static byte randByte()
        {
            return (byte)(random.Next(256));
        }

        public static double randDRange(double range)
        {
            return randD(-range,range);
        }
        public static bool chance(double probability)
        {
            return randD() < probability;
        }

        public static double randD()
        {
            return random.NextDouble();
        }

        public static double randD(double max)
        {
            return randD() * max;
        }

        public static double randD(double min,double max)
        {
            return randD(max - min) + min;
        }

        public static bool isKeyPressed(Key k)
        {
            return gameEngine.keyPressed[(int)k];
        }

        public static GameEngine gameEngine = new GameEngine();


        internal static double randAngle()
        {
            return randD(360.0);
        }

        internal static int randI()
        {
            return random.Next();
        }
        internal static int randI(int p1)
        {
            return random.Next(p1);
        }
        internal static int randI(int p1, int p2)
        {
            return random.Next(p1, p2);
        }

        public static void SetupSound(ref MediaPlayer gameSound, string filename)
        {
            if (gameSound == null)
            {
                gameSound = new MediaPlayer();
                G.CheckForAlternateContentDir(filename);
                Uri uri = new Uri(G.ContentDir + filename, UriKind.Relative);
                gameSound.Open(uri);
            }
        }


    }
}
