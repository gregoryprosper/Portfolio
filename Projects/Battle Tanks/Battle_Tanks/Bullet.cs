//************************************************
//
// (c) Copyright 2015 Dr. Thomas Fernandez
//
// All rights reserved.
//
//************************************************

using System;
using System.Collections.Generic;
using System.Linq;
using System.Media;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Controls;
using System.Windows.Media;
using System.Windows.Shapes;

namespace BattleTanks
{
    enum Origin{
        Player,
        Enemy
    }

    class Bullet : GameObject
    {

        public static MediaPlayer exploSound = null;
        public static MediaPlayer deflectSound = null;
        public static List<Bullet> list = new List<Bullet>();
        public Origin origin;

        public Bullet()
        {

            G.SetupSound(ref exploSound, "implosion3.wav");
            G.SetupSound(ref deflectSound, "deflect.wav");
            exploSound.Volume = 0.2;

            Ellipse baseElement = new Ellipse();
            baseElement.Fill = Brushes.LightYellow;
            baseElement.Height = 10;
            baseElement.Width = 10;


            element = baseElement;
            makeInactive();

            Bullet.list.Add(this);

            AddToGame();

        }


        public static Bullet getNextAvailable()
        {
            foreach(Bullet b in list )
            {
                if(!b.isActive)
                {
                    return b;
                }
            }
            return null;
        }

        public void initializeSpeed(double angle)
        {
            double vectorX = Math.Cos((Math.PI / 180) * angle);
            double vectorY = Math.Sin((Math.PI / 180) * angle);
            double magnitude = Math.Sqrt(Math.Pow(vectorX, 2) + Math.Pow(vectorY, 2));
            double unitVectorX = Math.Cos((Math.PI / 180) * angle) / magnitude;
            double unitVectorY = Math.Sin((Math.PI / 180) * angle) / magnitude;
            dX += unitVectorX * 10;
            dY += unitVectorY * 10;
        }


        double gravity = 0.0;
        double friction = 1.01;


        public int frameCount = 0;


        public override void update()
        {
            if (isActive)
            {
                frameCount--;
                if (frameCount <= 0) makeInactive();
                dY += gravity;
                if (Y < 0.0) makeInactive();
                else if (Y > G.gameHeight) makeInactive();
                else if (X < 0.0) makeInactive();
                else if (X > G.gameWidth) makeInactive();
                dX *= friction;
                dY *= friction;
                X += dX;
                Y += dY;

                foreach (EnemyTank e in EnemyTank.list)
                {
                    if (G.checkCollision(this, e) && origin == Origin.Player)
                    {
                        exploSound.Stop();
                        exploSound.Play();
                        e.state = TankState.Hit;
                        makeInactive();
                        frameCount = 15;
                    }
                }

                if (G.checkCollision(this, GameEngine.playerTank) && origin == Origin.Enemy)
                {
                    exploSound.Stop();
                    exploSound.Play();
                    GameEngine.playerTank.state = TankState.Hit;
                    makeInactive();
                    frameCount = 15;
                }

                if (G.checkCollision(this, GameEngine.playerTank.shield) && origin == Origin.Enemy)
                {
                    deflectSound.Stop();
                    deflectSound.Play();
                    makeInactive();
                    frameCount = 15;
                }
            }
        }
    }
}
