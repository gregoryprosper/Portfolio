//
//  ScoreViewController.m
//  Matchismo
//
//  Created by Gregory Prosper on 8/23/14.
//  Copyright (c) 2014 CS193p. All rights reserved.
//

#import "ScoreViewController.h"

@interface ScoreViewController ()
@property (weak, nonatomic) IBOutlet UILabel *date1;
@property (weak, nonatomic) IBOutlet UILabel *date2;
@property (weak, nonatomic) IBOutlet UILabel *date3;
@property (weak, nonatomic) IBOutlet UILabel *date4;
@property (weak, nonatomic) IBOutlet UILabel *date5;
@property (weak, nonatomic) IBOutlet UILabel *date6;
@property (weak, nonatomic) IBOutlet UILabel *date7;
@property (weak, nonatomic) IBOutlet UILabel *date8;
@property (weak, nonatomic) IBOutlet UILabel *date9;
@property (weak, nonatomic) IBOutlet UILabel *score1;
@property (weak, nonatomic) IBOutlet UILabel *score2;
@property (weak, nonatomic) IBOutlet UILabel *score3;
@property (weak, nonatomic) IBOutlet UILabel *score4;
@property (weak, nonatomic) IBOutlet UILabel *score5;
@property (weak, nonatomic) IBOutlet UILabel *score6;
@property (weak, nonatomic) IBOutlet UILabel *score7;
@property (weak, nonatomic) IBOutlet UILabel *score8;
@property (weak, nonatomic) IBOutlet UILabel *score9;
@property (weak, nonatomic) IBOutlet UILabel *duration1;
@property (weak, nonatomic) IBOutlet UILabel *duration2;
@property (weak, nonatomic) IBOutlet UILabel *duration3;
@property (weak, nonatomic) IBOutlet UILabel *duration4;
@property (weak, nonatomic) IBOutlet UILabel *duration5;
@property (weak, nonatomic) IBOutlet UILabel *duration6;
@property (weak, nonatomic) IBOutlet UILabel *duration7;
@property (weak, nonatomic) IBOutlet UILabel *duration8;
@property (weak, nonatomic) IBOutlet UILabel *duration9;
@property (weak, nonatomic) IBOutlet UILabel *game1;
@property (weak, nonatomic) IBOutlet UILabel *game2;
@property (weak, nonatomic) IBOutlet UILabel *game3;
@property (weak, nonatomic) IBOutlet UILabel *game4;
@property (weak, nonatomic) IBOutlet UILabel *game5;
@property (weak, nonatomic) IBOutlet UILabel *game6;
@property (weak, nonatomic) IBOutlet UILabel *game7;
@property (weak, nonatomic) IBOutlet UILabel *game8;
@property (weak, nonatomic) IBOutlet UILabel *game9;

@end

@implementation ScoreViewController

-(void)viewDidAppear:(BOOL)animated{
    NSArray *dateLabels = @[_date1,_date2,_date3,_date4,_date5,_date6,_date7,_date8,_date9];
    NSArray *scoreLabels = @[_score1,_score2,_score3,_score4,_score5,_score6,_score7,_score8,_score9];
    NSArray *gameLabels = @[_game1,_game2,_game3,_game4,_game5,_game6,_game7,_game8,_game9];
    NSArray *durationLabels = @[_duration1,_duration2,_duration3,_duration4,_duration5,_duration6,_duration7,_duration8,_duration9];
    
    NSArray *dates = [[NSUserDefaults standardUserDefaults] arrayForKey:@"dateArray"];
    NSArray *scores = [[NSUserDefaults standardUserDefaults] arrayForKey:@"scoreArray"];
    NSArray *durations = [[NSUserDefaults standardUserDefaults] arrayForKey:@"durationArray"];
    NSArray *games = [[NSUserDefaults standardUserDefaults] arrayForKey:@"gameArray"];
    
    UILabel *label = [[UILabel alloc]init];
    
    if (scores.count != 0) {
        for (int i = 0; i <= scores.count - 1; i++) {
            label = dateLabels[i];
            label.text = dates[i];
            label = scoreLabels[i];
            label.text = scores[i];
            label = durationLabels[i];
            label.text = durations[i];
            label = gameLabels[i];
            label.text = games[i];
        }
    }
    
}
@end
