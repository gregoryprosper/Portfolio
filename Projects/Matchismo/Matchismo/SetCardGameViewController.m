//
//  SetCardGameViewController.m
//  Matchismo
//
//  Created by Gregory Prosper on 8/14/14.
//  Copyright (c) 2014 CS193p. All rights reserved.
//

#import "SetCardGameViewController.h"
#import "SetCardDeck.h"
#import "SetCard.h"

@interface SetCardGameViewController ()
@property (weak, nonatomic) IBOutlet UIBarButtonItem *historyButton;
@property (weak, nonatomic) IBOutlet UIImageView *rulesImage;

@end


@implementation SetCardGameViewController
-(void)viewDidLoad{
    [self updateUI];
}

-(void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event{
    
    UITouch *touch = [touches anyObject];
    
    if ([touch view] == self.rulesImage) {
        [self.rulesImage removeFromSuperview];
        self.historyButton.enabled = YES;
    }
    
    [super touchesBegan:touches withEvent:event];
}

-(Deck*)createDeck{
    return [[SetCardDeck alloc]init];
}

-(UIImage*)backgroundImageForCard:(Card *)card{
    if (card.isChosen) {
        return [UIImage imageNamed:@"cardFrontShaded@1x"];
    }
    else return [UIImage imageNamed:@"cardFront@1x"];
}

-(NSAttributedString*)titleForCard:(SetCard *)card{
    
    NSString *title = [[NSMutableString alloc]init];
    int number = [card.number intValue];
    int symbol = [card.symbol intValue];
    int shading = [card.shading intValue];
    int color = [card.color intValue];
    
    title = [self convertToSymbols:number :symbol];
    NSDictionary *attrDict = [self applyAttributes:shading :color];
    NSAttributedString *aTitle = [[NSAttributedString alloc]initWithString:title attributes:attrDict];
    
    return aTitle;
}


@end

