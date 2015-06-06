//
//  CardGameViewController.m
//  Matchismo
//
//  Created by Gregory Prosper on 8/7/14.
//  Copyright (c) 2014 CS193p. All rights reserved.
//

#import "CardGameViewController.h"
#import "Card.h"
#import "CardMatchingGame.h"
#import "HistoryViewController.h"
#import "PlayingCardGameViewController.h"
#import "SetCardGameViewController.h"
#import "PlayingCardView.h"
#import "PlayingCard.h"


@interface CardGameViewController ()
@property (strong, nonatomic) IBOutletCollection(UIButton) NSArray *cardButtons;
@property (strong, nonatomic) IBOutletCollection(PlayingCardView) NSArray *cardViews;
@property (nonatomic,strong) CardMatchingGame *game;
@property (weak, nonatomic) IBOutlet UILabel *scoreLabel;
@property (weak, nonatomic) IBOutlet UILabel *logLabel;
@property (nonatomic, strong) NSMutableArray *attributedLogList;
@property (nonatomic,strong) NSTimer *timer;
@property (nonatomic) int durationTracker;
@end

@implementation CardGameViewController

-(void)viewWillAppear:(BOOL)animated{
    [self updateUI];
}

- (IBAction)touchCardButton:(UIButton *)sender {
    [self.timer invalidate];
    self.timer = [NSTimer scheduledTimerWithTimeInterval:1.0 target:self selector:@selector(tick) userInfo:nil repeats:YES];
    self.logLabel.text = @"";
    int chosenButtonIndex = (int)[self.cardButtons indexOfObject:sender];
    [self.game chooseCardAtIndex:chosenButtonIndex];
    [self updateUI];
}

- (IBAction)touchedCardView:(UIView *)sender {
    [self.timer invalidate];
    self.timer = [NSTimer scheduledTimerWithTimeInterval:1.0 target:self selector:@selector(tick) userInfo:nil repeats:YES];
    self.logLabel.text = @"";
    int chosenButtonIndex = (int)[self.cardViews indexOfObject:sender];
    [self.game chooseCardAtIndex:chosenButtonIndex];
    [self updateUI];
}



-(void)tick{
    self.durationTracker++;
    NSLog(@"%d",self.durationTracker);
}


-(void)updateUI{
    
    if ([self isKindOfClass:[SetCardGameViewController class]]) {
        
        for (UIButton *cardView in self.cardButtons) {
            int cardIndex = (int)[self.cardButtons indexOfObject:cardView];
            Card *card = [self.game cardAtIndex:cardIndex];
            [cardView setAttributedTitle:[self titleForCard:card] forState:UIControlStateNormal];
            [cardView setBackgroundImage:[self backgroundImageForCard:card] forState:UIControlStateNormal];
            self.scoreLabel.text = [NSString stringWithFormat:@"Score: %lu",(unsigned long)self.game.score];
            [self updateLogLabel];
        }
    }
    else{
        
        for (PlayingCardView *cardView in self.cardViews) {
            int cardIndex = (int)[self.cardViews indexOfObject:cardView];
            PlayingCard *card = (PlayingCard*) [self.game cardAtIndex:cardIndex];
//            cardView.suit = @"♥️";
//            cardView.rank = 13;
            cardView.suit = card.suit;
            cardView.rank = card.rank;
            if (card.chosen) {
                cardView.faceUp = true;
            }
            else{
                cardView.faceUp = false;
            }
            self.scoreLabel.text = [NSString stringWithFormat:@"Score: %lu",(unsigned long)self.game.score];
            [self updateLogLabel];
        }
    }
         
    
}


-(NSString*)convertToSymbols:(NSInteger)number :(NSInteger)symbol{
    NSString *title = [[NSString alloc]init];
    
    if (symbol == 1) {
        for (int i = 0; i < number; i++) {
            title = [title stringByAppendingString:@"▲"];
        }
    }
    else if (symbol == 2){
        for (int i = 0; i < number; i++) {
            title = [title stringByAppendingString:@"■"];
        }
    }
    else if (symbol == 3){
        for (int i = 0; i < number; i++) {
            title = [title stringByAppendingString:@"●"];
        }
    }
    
    return title;
}

-(NSDictionary*)applyAttributes:(NSInteger)shading :(NSInteger)color{
    NSMutableDictionary *attrDict = [[NSMutableDictionary alloc]init];
    
    if (color == 1) {
        [attrDict setObject:[UIColor purpleColor] forKey:NSStrokeColorAttributeName];
        [attrDict setObject:@-5 forKey:NSStrokeWidthAttributeName];
        
        if (shading == 1) {
            [attrDict setObject:[[UIColor purpleColor] colorWithAlphaComponent:0.0] forKey:NSForegroundColorAttributeName];
        }
        else if (shading == 2){
            [attrDict setObject:[[UIColor purpleColor] colorWithAlphaComponent:0.5] forKey:NSForegroundColorAttributeName];
        }
        else if (shading == 3){
            [attrDict setObject:[[UIColor purpleColor] colorWithAlphaComponent:1.0] forKey:NSForegroundColorAttributeName];
        }
    }else if (color == 2){
        [attrDict setObject:[UIColor redColor] forKey:NSStrokeColorAttributeName];
        [attrDict setObject:@-5 forKey:NSStrokeWidthAttributeName];
        
        if (shading == 1) {
            [attrDict setObject:[[UIColor redColor] colorWithAlphaComponent:0.0] forKey:NSForegroundColorAttributeName];
        }
        else if (shading == 2){
            [attrDict setObject:[[UIColor redColor] colorWithAlphaComponent:0.5] forKey:NSForegroundColorAttributeName];
        }
        else if (shading == 3){
            [attrDict setObject:[[UIColor redColor] colorWithAlphaComponent:1.0] forKey:NSForegroundColorAttributeName];
        }
    }else if (color == 3){
        [attrDict setObject:[UIColor blueColor] forKey:NSStrokeColorAttributeName];
        [attrDict setObject:@-5 forKey:NSStrokeWidthAttributeName];
        
        if (shading == 1) {
            [attrDict setObject:[[UIColor blueColor] colorWithAlphaComponent:0.0] forKey:NSForegroundColorAttributeName];
        }
        else if (shading == 2){
            [attrDict setObject:[[UIColor blueColor] colorWithAlphaComponent:0.5] forKey:NSForegroundColorAttributeName];
        }
        else if (shading == 3){
            [attrDict setObject:[[UIColor blueColor] colorWithAlphaComponent:1.0] forKey:NSForegroundColorAttributeName];
        }
    }
    
    return attrDict;
}

-(void)updateLogLabel{
    if (self.game.gameMode == 0) {
        if (self.game.cardsSelected > 1) {
            self.logLabel.text = [self.game.log lastObject];
        }
    }
    else if (self.game.gameMode == 1){
        
        NSString *stringToBeParsed = [[self.game.log lastObject] substringToIndex:12];
        
        int cardOneNumber = [[stringToBeParsed substringWithRange:NSMakeRange(0, 1)] intValue];
        int cardOneSymbol = [[stringToBeParsed substringWithRange:NSMakeRange(1, 1)] intValue];
        int cardOneShading = [[stringToBeParsed substringWithRange:NSMakeRange(2, 1)] intValue];
        int cardOneColor = [[stringToBeParsed substringWithRange:NSMakeRange(3, 1)] intValue];
        
        int cardTwoNumber = [[stringToBeParsed substringWithRange:NSMakeRange(4, 1)] intValue];
        int cardTwoSymbol = [[stringToBeParsed substringWithRange:NSMakeRange(5, 1)] intValue];
        int cardTwoShading = [[stringToBeParsed substringWithRange:NSMakeRange(6, 1)] intValue];
        int cardTwoColor = [[stringToBeParsed substringWithRange:NSMakeRange(7, 1)] intValue];
        
        int cardThreeNumber = [[stringToBeParsed substringWithRange:NSMakeRange(8, 1)] intValue];
        int cardThreeSymbol = [[stringToBeParsed substringWithRange:NSMakeRange(9, 1)] intValue];
        int cardThreeShading = [[stringToBeParsed substringWithRange:NSMakeRange(10, 1)] intValue];
        int cardThreeColor = [[stringToBeParsed substringWithRange:NSMakeRange(11, 1)] intValue];
        
        NSString *card1 = [self convertToSymbols:cardOneNumber :cardOneSymbol];
        NSString *card2 = [self convertToSymbols:cardTwoNumber :cardTwoSymbol];
        NSString *card3 = [self convertToSymbols:cardThreeNumber :cardThreeSymbol];
        
        NSString *stringAfterParsing = [NSString stringWithFormat:@"%@ & %@ & %@ %@",card1,card2,card3,[[self.game.log lastObject] substringWithRange:NSMakeRange(12, [[self.game.log lastObject] length] - 12)]];
        
        NSRange firstCard = NSMakeRange(0, card1.length);
        NSRange secondCard = NSMakeRange(card1.length + 3, card2.length);
        NSRange thirdCard = NSMakeRange((card1.length + 3) + (card2.length + 3), card3.length);
        
        NSDictionary *attrDictForCard1 = [self applyAttributes:cardOneShading :cardOneColor];
        NSDictionary *attrDictForCard2 = [self applyAttributes:cardTwoShading :cardTwoColor];
        NSDictionary *attrDictForCard3 = [self applyAttributes:cardThreeShading :cardThreeColor];
        
        NSMutableAttributedString *label = [[NSMutableAttributedString alloc]initWithString:stringAfterParsing];
        if ([self.game.log count]) {
            [label addAttributes:attrDictForCard1 range:firstCard];
            [label addAttributes:attrDictForCard2 range:secondCard];
            [label addAttributes:attrDictForCard3 range:thirdCard];
            [label addAttribute:NSFontAttributeName value:[UIFont preferredFontForTextStyle:UIFontTextStyleBody] range:NSMakeRange(0,label.length)];
        }
        
        if (self.game.cardsSelected > 1) {
            self.logLabel.attributedText = label;
            
            if (![[self.attributedLogList.lastObject string] isEqualToString:[label string]]) {
                [self.attributedLogList addObject:label];

            }
        }
    }
    
}

- (IBAction)reDeal:(UIButton *)sender {
    self.game = nil;
    [self.timer invalidate];
    self.durationTracker = 0;
    self.attributedLogList = nil;
    self.logLabel.text = @"";
    [self updateUI];
}

-(NSAttributedString*)titleForCard:(Card *)card{
    if (card.isChosen) {
        return [[NSAttributedString alloc]initWithString:card.contents];
    }
    else return [[NSAttributedString alloc] initWithString:@""];
}

-(UIImage*)backgroundImageForCard:(Card *)card{
    return nil;
}

-(CardMatchingGame*)game{
    if (!_game) {
        if ([self isKindOfClass:[SetCardGameViewController class]]) {
            _game = [[CardMatchingGame alloc]initWithCardCount:[self.cardButtons count] usingDeck:[self createDeck]];
        }
        else{
            _game = [[CardMatchingGame alloc]initWithCardCount:[self.cardViews count] usingDeck:[self createDeck]];
        }
        
    }
    return _game;
}

-(NSMutableArray*)attributedLogList{
    if (!_attributedLogList) {
        _attributedLogList = [[NSMutableArray alloc]init];
    }
    return _attributedLogList;
}

-(void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender{
    if ([segue.identifier isEqualToString:@"toMatchPlayingHistory"]) {
        if ([segue.destinationViewController isKindOfClass:[HistoryViewController class]]) {
            HistoryViewController *vc = (HistoryViewController*)segue.destinationViewController;
            
            vc.history = self.game.log;
            vc.viewPerformingSegue = @"Playing";
        }
    }
    else if ([segue.identifier isEqualToString:@"toMatchSetHistory"]){
        if ([segue.destinationViewController isKindOfClass:[HistoryViewController class]]) {
            HistoryViewController *vc = (HistoryViewController*)segue.destinationViewController;
            
            vc.history = self.attributedLogList;
            vc.viewPerformingSegue = @"Set";
        }
    }
}


-(Deck*)createDeck{
    return nil;
}

-(NSUInteger)getIndexOfNewScore:(NSMutableArray*)currentArray :(NSString*)element{
    for (int i = (int) currentArray.count - 1; i >= 0; i--){
        if ([element intValue] < [currentArray[i] intValue]) {
            return i+1;
        }
    }
    return 0;
}

-(void)viewWillDisappear:(BOOL)animated{
    [self.timer invalidate];
    NSArray *scoreArray = [[NSUserDefaults standardUserDefaults] arrayForKey:@"scoreArray"];
    NSArray *gameArray = [[NSUserDefaults standardUserDefaults] arrayForKey:@"gameArray"];
    NSArray *dateArray = [[NSUserDefaults standardUserDefaults] arrayForKey:@"dateArray"];
    NSArray *durationArray = [[NSUserDefaults standardUserDefaults] arrayForKey:@"durationArray"];
     
    if (scoreArray != nil) {
        if (scoreArray.count == 1 && self.game.score > [[scoreArray lastObject] intValue]){ //Arrays only have one Score
            
            if ([self isKindOfClass:[PlayingCardGameViewController class]]){
                
                NSString *score = [NSString stringWithFormat:@"%lu",(unsigned long)self.game.score];
                NSString *game = @"Match";
                NSString *date = [[[NSDate date] description]substringWithRange:NSMakeRange(5, 6)];
                NSString *duration = [[NSString stringWithFormat:@"%d",self.durationTracker] stringByAppendingString:@" secs"];
                
                NSMutableArray *mutableScoreArray = [scoreArray mutableCopy];
                NSMutableArray *mutableGameArray = [gameArray mutableCopy];
                NSMutableArray *mutableDateArray = [dateArray mutableCopy];
                NSMutableArray *mutableDurationArray = [durationArray mutableCopy];
                
                [mutableScoreArray insertObject:score atIndex:0];
                [mutableGameArray insertObject:game atIndex:0];
                [mutableDateArray insertObject:date atIndex:0];
                [mutableDurationArray insertObject:duration atIndex:0];
                
                [[NSUserDefaults standardUserDefaults] setObject:mutableScoreArray forKey:@"scoreArray"];
                [[NSUserDefaults standardUserDefaults] setObject:mutableGameArray forKey:@"gameArray"];
                [[NSUserDefaults standardUserDefaults] setObject:mutableDateArray forKey:@"dateArray"];
                [[NSUserDefaults standardUserDefaults] setObject:mutableDurationArray forKey:@"durationArray"];
            }
            else if ([self isKindOfClass:[SetCardGameViewController class]]){
                
                NSString *score = [NSString stringWithFormat:@"%lu",(unsigned long)self.game.score];
                NSString *game = @"Set";
                NSString *date = [[[NSDate date] description]substringWithRange:NSMakeRange(5, 6)];
                NSString *duration = [[NSString stringWithFormat:@"%d",self.durationTracker] stringByAppendingString:@" secs"];
                
                NSMutableArray *mutableScoreArray = [scoreArray mutableCopy];
                NSMutableArray *mutableGameArray = [gameArray mutableCopy];
                NSMutableArray *mutableDateArray = [dateArray mutableCopy];
                NSMutableArray *mutableDurationArray = [durationArray mutableCopy];
                
                [mutableScoreArray insertObject:score atIndex:0];
                [mutableGameArray insertObject:game atIndex:0];
                [mutableDateArray insertObject:date atIndex:0];
                [mutableDurationArray insertObject:duration atIndex:0];
                
                [[NSUserDefaults standardUserDefaults] setObject:mutableScoreArray forKey:@"scoreArray"];
                [[NSUserDefaults standardUserDefaults] setObject:mutableGameArray forKey:@"gameArray"];
                [[NSUserDefaults standardUserDefaults] setObject:mutableDateArray forKey:@"dateArray"];
                [[NSUserDefaults standardUserDefaults] setObject:mutableDurationArray forKey:@"durationArray"];
            }
        }
        else if (self.game.score > [[scoreArray lastObject] intValue] && ![scoreArray containsObject:[NSString stringWithFormat:@"%lu",(unsigned long)self.game.score]]){ //Array has more than one Score
            
            if ([self isKindOfClass:[PlayingCardGameViewController class]]){
                
                NSString *score = [NSString stringWithFormat:@"%lu",(unsigned long)self.game.score];
                NSString *game = @"Match";
                NSString *date = [[[NSDate date] description]substringWithRange:NSMakeRange(5, 6)];
                NSString *duration = [[NSString stringWithFormat:@"%d",self.durationTracker] stringByAppendingString:@" secs"];
                
                NSMutableArray *mutableScoreArray = [scoreArray mutableCopy];
                NSMutableArray *mutableGameArray = [gameArray mutableCopy];
                NSMutableArray *mutableDateArray = [dateArray mutableCopy];
                NSMutableArray *mutableDurationArray = [durationArray mutableCopy];
                
                [mutableScoreArray insertObject:score atIndex:[self getIndexOfNewScore:mutableScoreArray :score]];
                [mutableGameArray insertObject:game atIndex:[self getIndexOfNewScore:mutableScoreArray :score]];
                [mutableDateArray insertObject:date atIndex:[self getIndexOfNewScore:mutableScoreArray :score]];
                [mutableDurationArray insertObject:duration atIndex:[self getIndexOfNewScore:mutableScoreArray :score]];
                
                if ([mutableScoreArray indexOfObject:mutableScoreArray.lastObject] == 9) {
                    [mutableScoreArray removeLastObject];
                    [mutableGameArray removeLastObject];
                    [mutableDateArray removeLastObject];
                    [mutableDurationArray removeLastObject];
                }
                
                [[NSUserDefaults standardUserDefaults] setObject:mutableScoreArray forKey:@"scoreArray"];
                [[NSUserDefaults standardUserDefaults] setObject:mutableGameArray forKey:@"gameArray"];
                [[NSUserDefaults standardUserDefaults] setObject:mutableDateArray forKey:@"dateArray"];
                [[NSUserDefaults standardUserDefaults] setObject:mutableDurationArray forKey:@"durationArray"];
            }
            else if ([self isKindOfClass:[SetCardGameViewController class]]){
                
                NSString *score = [NSString stringWithFormat:@"%lu",(unsigned long)self.game.score];
                NSString *game = @"Set";
                NSString *date = [[[NSDate date] description]substringWithRange:NSMakeRange(5, 6)];
                NSString *duration = [[NSString stringWithFormat:@"%d",self.durationTracker] stringByAppendingString:@" secs"];
                
                NSMutableArray *mutableScoreArray = [scoreArray mutableCopy];
                NSMutableArray *mutableGameArray = [gameArray mutableCopy];
                NSMutableArray *mutableDateArray = [dateArray mutableCopy];
                NSMutableArray *mutableDurationArray = [durationArray mutableCopy];
                
                [mutableScoreArray insertObject:score atIndex:[self getIndexOfNewScore:mutableScoreArray :score]];
                [mutableGameArray insertObject:game atIndex:[self getIndexOfNewScore:mutableScoreArray :score]];
                [mutableDateArray insertObject:date atIndex:[self getIndexOfNewScore:mutableScoreArray :score]];
                [mutableDurationArray insertObject:duration atIndex:[self getIndexOfNewScore:mutableScoreArray :score]];
                
                if ([mutableScoreArray indexOfObject:mutableScoreArray.lastObject] == 9) {
                    [mutableScoreArray removeLastObject];
                    [mutableGameArray removeLastObject];
                    [mutableDateArray removeLastObject];
                    [mutableDurationArray removeLastObject];
                }
                
                [[NSUserDefaults standardUserDefaults] setObject:mutableScoreArray forKey:@"scoreArray"];
                [[NSUserDefaults standardUserDefaults] setObject:mutableGameArray forKey:@"gameArray"];
                [[NSUserDefaults standardUserDefaults] setObject:mutableDateArray forKey:@"dateArray"];
                [[NSUserDefaults standardUserDefaults] setObject:mutableDurationArray forKey:@"durationArray"];
            }
        }
    }
    else{//Arrays are Empty
        if (self.game.score != 0){
            if ([self isKindOfClass:[PlayingCardGameViewController class]]){
                NSString *score = [NSString stringWithFormat:@"%lu",(unsigned long)self.game.score];
                NSString *game = @"Match";
                NSString *date = [[[NSDate date] description]substringWithRange:NSMakeRange(5, 6)];
                NSString *duration = [[NSString stringWithFormat:@"%d",self.durationTracker] stringByAppendingString:@" secs"];
                
                scoreArray = [[NSArray alloc]initWithObjects:score, nil];
                gameArray = [[NSArray alloc]initWithObjects:game, nil];
                dateArray = [[NSArray alloc]initWithObjects:date, nil];
                durationArray = [[NSArray alloc]initWithObjects:duration, nil];
                [[NSUserDefaults standardUserDefaults] setObject:scoreArray forKey:@"scoreArray"];
                [[NSUserDefaults standardUserDefaults] setObject:gameArray forKey:@"gameArray"];
                [[NSUserDefaults standardUserDefaults] setObject:dateArray forKey:@"dateArray"];
                [[NSUserDefaults standardUserDefaults] setObject:durationArray forKey:@"durationArray"];
            }
            else if ([self isKindOfClass:[SetCardGameViewController class]]){
                NSString *score = [NSString stringWithFormat:@"%lu",(unsigned long)self.game.score];
                NSString *game = @"Set";
                NSString *date = [[[NSDate date] description]substringWithRange:NSMakeRange(5, 6)];
                NSString *duration = [NSString stringWithFormat:@"%d",self.durationTracker];
                
                scoreArray = [[NSArray alloc]initWithObjects:score, nil];
                gameArray = [[NSArray alloc]initWithObjects:game, nil];
                dateArray = [[NSArray alloc]initWithObjects:date, nil];
                durationArray = [[NSArray alloc]initWithObjects:duration, nil];
                [[NSUserDefaults standardUserDefaults] setObject:scoreArray forKey:@"scoreArray"];
                [[NSUserDefaults standardUserDefaults] setObject:gameArray forKey:@"gameArray"];
                [[NSUserDefaults standardUserDefaults] setObject:dateArray forKey:@"dateArray"];
                [[NSUserDefaults standardUserDefaults] setObject:durationArray forKey:@"durationArray"];
            }
        }
        
    }
    [self reDeal:nil]; //When view disapears game is reset
}
@end
