//
//  PlayingCardGameViewController.m
//  Matchismo
//
//  Created by Gregory Prosper on 8/12/14.
//  Copyright (c) 2014 CS193p. All rights reserved.
//

#import "PlayingCardGameViewController.h"
#import "PlayingCardDeck.h"
#import "HistoryViewController.h"

@interface PlayingCardGameViewController ()
@property (weak, nonatomic) IBOutlet UIImageView *rulesImage;
@property (weak, nonatomic) IBOutlet UIBarButtonItem *historyButton;

@end

@implementation PlayingCardGameViewController
-(Deck*)createDeck{
    return [[PlayingCardDeck alloc]init];
}

-(void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event{
    
    UITouch *touch = [touches anyObject];
    
    if ([touch view] == self.rulesImage) {
        [self.rulesImage removeFromSuperview];
        self.historyButton.enabled = YES;
    }
    
    [super touchesBegan:touches withEvent:event];
}

@end
