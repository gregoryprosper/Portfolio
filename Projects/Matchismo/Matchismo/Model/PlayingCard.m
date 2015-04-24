//
//  PlayingCard.m
//  Matchismo
//
//  Created by Gregory Prosper on 8/7/14.
//  Copyright (c) 2014 CS193p. All rights reserved.
//

#import "PlayingCard.h"

@implementation PlayingCard

-(NSString*)contents{
    
    NSArray *rankStrings = [PlayingCard rankStrings];
    return [rankStrings[self.rank] stringByAppendingString:self.suit];
}

@synthesize suit = _suit;

+(NSArray*)validSuits{
    return @[@"♣️",@"♠️",@"♥️",@"♦️"];
}

+(NSArray*)rankStrings{
    return @[@"?",@"A",@"2",@"3",@"4",@"5",@"6",@"7",@"8",@"9",@"10",@"J",@"Q",@"K"];
}

+(NSUInteger)maxRank{
    return [[self rankStrings] count] - 1;
}

-(void)setSuit:(NSString *)suit{
    
    if ([[PlayingCard validSuits] containsObject:suit]) {
        _suit = suit;
    }
}

-(NSString*)suit{
    return _suit ? _suit : @"?";
}

-(void)setRank:(NSInteger)rank{
    if (rank <= [PlayingCard maxRank]) {
        _rank = rank;
    }
}

-(int)match:(NSArray *)otherCards{
    
    int score = 0;
    
    if (otherCards.count == 1) {
        for (PlayingCard *otherCard in otherCards){
            if (otherCard.rank == self.rank) {
                score += 4;
                self.cardLog = [NSString stringWithFormat:@"Matched %@ with %@ for ",self.contents,otherCard.contents];
            }
            else if (otherCard.suit == self.suit){
                score += 1;
                self.cardLog = [NSString stringWithFormat:@"Matched %@ with %@ for ",self.contents,otherCard.contents];
            }
            
            if (score == 0) {
                self.cardLog = [NSString stringWithFormat:@"No match between %@ & %@ ",self.contents,otherCard.contents];
            }
        }
    }
    else if (otherCards.count == 2){
        PlayingCard *firstCard = otherCards.firstObject;
        PlayingCard *secondCard = otherCards.lastObject;
        if (firstCard.rank == self.rank && secondCard.rank == self.rank) {
            score += 20;
            self.cardLog = [NSString stringWithFormat:@"Match between %@, %@ & %@ for ",self.contents,firstCard.contents,secondCard.contents];
        }
        else if (firstCard.suit == self.suit && secondCard.suit == self.suit){
            score += 10;
            self.cardLog = [NSString stringWithFormat:@"Match between %@, %@ & %@ for ",self.contents,firstCard.contents,secondCard.contents];
        }
        else if (firstCard.rank == self.rank || self.rank == secondCard.rank || firstCard.rank == secondCard.rank) {
            score += 4;
            
            if (firstCard.rank == self.rank) {
                self.cardLog = [NSString stringWithFormat:@"Matched %@ with %@ for ",self.contents,firstCard.contents];
            }
            else if (self.rank == secondCard.rank){
                self.cardLog = [NSString stringWithFormat:@"Matched %@ with %@ for ",self.contents,secondCard.contents];
            }
            else if (firstCard.rank == secondCard.rank){
                self.cardLog = [NSString stringWithFormat:@"Matched %@ with %@ for ",firstCard.contents,secondCard.contents];
            }
        }
        else if (firstCard.suit == self.suit || self.suit == secondCard.suit || firstCard.suit == secondCard.suit) {
            score += 1;
            
            if (firstCard.suit == self.suit) {
                self.cardLog = [NSString stringWithFormat:@"Matched %@ with %@ for ",self.contents,firstCard.contents];
            }
            else if (self.suit == secondCard.suit){
                self.cardLog = [NSString stringWithFormat:@"Matched %@ with %@ for ",self.contents,secondCard.contents];
            }
            else if (firstCard.suit == secondCard.suit){
                self.cardLog = [NSString stringWithFormat:@"Matched %@ with %@ for ",firstCard.contents,secondCard.contents];
            }
        }
        
        if (score == 0) {
            self.cardLog = [NSString stringWithFormat:@"No match between %@, %@ & %@ ",self.contents,firstCard.contents,secondCard.contents];
        }
    }
    
    return score;
}
@end
