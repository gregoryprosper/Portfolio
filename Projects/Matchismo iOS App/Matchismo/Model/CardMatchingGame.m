//
//  CardMatchingGame.m
//  Matchismo
//
//  Created by Gregory Prosper on 8/9/14.
//  Copyright (c) 2014 CS193p. All rights reserved.
//

#import "CardMatchingGame.h"
#import "Deck.h"
#import "Card.h"
#import "SetCardDeck.h"

@interface CardMatchingGame()
@property (nonatomic,readwrite) NSUInteger score;
@property (nonatomic,strong) NSMutableArray *cards;
@end

@implementation CardMatchingGame

-(NSMutableArray*)cards{
    if (!_cards) {
        _cards = [[NSMutableArray alloc]init];
    }
    return _cards;
}

-(NSMutableArray*)log{
    if (!_log) {
        _log = [[NSMutableArray alloc]init];
    }
    return _log;
}

-(instancetype)initWithCardCount:(NSUInteger)count usingDeck:(Deck *)deck{
    
    self = [super init];
    
    if (self) {
        if ([deck isKindOfClass:[SetCardDeck class]]) {
            self.gameMode = 1;
        }
        
        for (int i = 0; i < count; i++) {
            Card *card = [deck drawRandomCard];
            if (card) {
                [self.cards addObject:card];
            }
            else{
                self = nil;
                break;
            }
        }
    }
    return self;
}

-(Card*)cardAtIndex:(NSUInteger)index{
    return (index < self.cards.count) ? self.cards[index] :nil;
}

static const int MISMATCH_PENALTY = 2;
static const int MATCH_BONUS = 5;
static const int COST_TO_CHOOSE = 1;

-(void)chooseCardAtIndex:(NSUInteger)index{
    
    Card *card = [self cardAtIndex:index];
    NSMutableArray *cardsToBeCompared =[[NSMutableArray alloc]init];
    self.cardsSelected = 1;
    
    if (!card.isMatched) {
        
        if (card.isChosen) {
            card.chosen = NO;
        }
        else{
            for (Card *otherCard in self.cards) {
                if (otherCard.isChosen && !otherCard.isMatched) {
                    [cardsToBeCompared addObject:otherCard];
                }
            }
            
            //match against other chosen cards
            if (cardsToBeCompared.count == self.gameMode + 1) {
                self.cardsSelected++;
                int matchScore = [card match:cardsToBeCompared];
                if (matchScore) {
                    self.score += matchScore * MATCH_BONUS;
                    card.cardLog = [card.cardLog stringByAppendingString:[NSString stringWithFormat:@"%d points!",matchScore * MATCH_BONUS]];
                    card.matched = YES;
                    
                    for (Card *otherCard in cardsToBeCompared) {
                        otherCard.matched = YES;
                    }
                }
                else{
                    
                    if (self.score > 0) {
                        self.score -= MISMATCH_PENALTY;
                        int scoreSignChangeMoniter = (int) self.score;
                        if (scoreSignChangeMoniter < 0) {
                            self.score = 0;
                        }
                        card.cardLog = [card.cardLog stringByAppendingString:[NSString stringWithFormat:@"(%d point penalty)",MISMATCH_PENALTY]];
                    }
                    
                    for (Card *otherCard in cardsToBeCompared) {
                        otherCard.chosen = NO;
                    }
                    
                }
                
            }
            if (self.score > 0 && self.gameMode == 0) {
                self.score -= COST_TO_CHOOSE;
            }
            
            if (card.cardLog) {
                [self.log addObject:card.cardLog];
            }
            card.chosen = YES;
        }
    }
}

@end
