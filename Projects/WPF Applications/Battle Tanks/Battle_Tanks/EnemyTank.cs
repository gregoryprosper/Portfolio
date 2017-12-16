//************************************************
//
// (c) Copyright 2015 Gregory Prosper
//
// All rights reserved.
//
//************************************************

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Media.Imaging;
using System.Windows.Threading;

namespace BattleTanks
{
    enum EnemyActionState
    {
        Rotating, Moving, Scouting, Shooting
    }

    struct ActionStateDurations
    {
        public int rotating;
        public int moving;
        public int scouting;
        public int shooting;

        public ActionStateDurations(int rotating, int moving, int scouting, int shooting)
        {
            this.rotating = rotating;
            this.moving = moving;
            this.scouting = scouting;
            this.shooting = shooting;
        }
    }

    class EnemyTank : Tank
    {
        static public List<EnemyTank> list = new List<EnemyTank>();
        static BitmapImage bMap = null;

        private DispatcherTimer timer = new DispatcherTimer();
        private EnemyActionState actionState = EnemyActionState.Rotating;
        private ActionStateDurations durations = new ActionStateDurations(G.randI(0, 2), G.randI(0, 8), G.randI(0, 1), G.randI(0, 3));

        private int timerTracker = 0;

        public EnemyTank()
        {
            UseImage("enemyTank.png", bMap);
            Scale = 0.75;

            X = G.randD(ScaledWidth / 2.0, G.gameWidth - ScaledWidth / 2.0);
            Angle = 90;

            timer.Tick += timer_Tick;
            timer.Interval = TimeSpan.FromMilliseconds(250);
            timer.Start();

            EnemyTank.list.Add(this);
            AddToGame();
        }

        private void timer_Tick(object sender, EventArgs e)
        {
            timerTracker++;

            if (actionState == EnemyActionState.Rotating && timerTracker > durations.rotating)
            {
                actionState = EnemyActionState.Moving;
                timerTracker = 0;
            }
            else if (actionState == EnemyActionState.Moving && timerTracker > durations.moving){
                actionState = EnemyActionState.Scouting;
                timerTracker = 0;
            }
            else if (actionState == EnemyActionState.Scouting && timerTracker > durations.scouting)
            {
                actionState = EnemyActionState.Shooting;
                timerTracker = 0;
            }
            else if (actionState == EnemyActionState.Shooting && timerTracker > durations.shooting)
            {
                actionState = EnemyActionState.Rotating;
                timerTracker = 0;
                durations = new ActionStateDurations(G.randI(0, 2), G.randI(0, 8), G.randI(0, 1), G.randI(0, 3));
            }
        }

         public override void update()
        {
            if (isActive)
            {
                frameCount++;

                if(actionState == EnemyActionState.Rotating){
                    Angle += G.randI(-10,20);
                }
                else if(actionState == EnemyActionState.Moving){
                    moveForward();
                }
                else if(actionState == EnemyActionState.Scouting){
                    double diffVectorX = X - GameEngine.playerTank.X;
                    double diffVectorY = Y - GameEngine.playerTank.Y;
                    double angle = Math.Atan(diffVectorY / diffVectorX) * (180 / Math.PI);
                    Angle = getAngleToTarget() + accuracyError();
                }
                else if(actionState == EnemyActionState.Shooting && frameCount > 25){
                    shoot(this);
                }
              
                if (state == TankState.Hit){
                    takeHit(15);
                    state = TankState.Clear;
                }
            }
        }

         private double accuracyError()
         {
             double distanceBetweenTarget = Math.Sqrt(Math.Pow(X - GameEngine.playerTank.X, 2) + Math.Pow(Y - GameEngine.playerTank.Y, 2));
             return distanceBetweenTarget / 75;
         }

         private double getAngleToTarget()
         {
             double diffVectorX = X - GameEngine.playerTank.X;
             double diffVectorY = Y - GameEngine.playerTank.Y;
             double angle = Math.Atan(diffVectorY / diffVectorX) * (180 / Math.PI);
             return X < GameEngine.playerTank.X ? angle : (angle + 180);
         }
    }
}
