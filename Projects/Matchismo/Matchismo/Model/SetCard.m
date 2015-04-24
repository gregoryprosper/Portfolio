//
//  SetCard.m
//  Matchismo
//
//  Created by Gregory Prosper on 8/14/14.
//  Copyright (c) 2014 CS193p. All rights reserved.
//

#import "SetCard.h"

@implementation SetCard

-(NSString*)contents{
    return nil;
}

+(NSArray*)elements{
    return @[@1,@2,@3];
}

-(int)match:(NSArray *)otherCards{
    int score = 0;
    int set = 0; //when set = 4, a set is found
    SetCard *firstCard = otherCards.firstObject;
    SetCard *secondCard = otherCards.lastObject;
    
    if (self.number == firstCard.number && self.number == secondCard.number && firstCard.number == secondCard.number) {
        set++;
    }
    
    if (self.symbol == firstCard.symbol && self.symbol == secondCard.symbol && firstCard.symbol == secondCard.symbol) {
        set++;
    }
    
    if (self.shading == firstCard.shading && self.shading == secondCard.shading && firstCard.shading == secondCard.shading) {
        set++;
    }
    
    if (self.color == firstCard.color && self.color == secondCard.color && firstCard.color == secondCard.color) {
        set++;
    }
    
    if (self.number != firstCard.number && self.number != secondCard.number && firstCard.number != secondCard.number) {
        set++;
    }
    
    if (self.symbol != firstCard.symbol && self.symbol != secondCard.symbol && firstCard.symbol != secondCard.symbol) {
        set++;
    }
    
    if (self.shading != firstCard.shading && self.shading != secondCard.shading && firstCard.shading != secondCard.shading) {
        set++;
    }
    
    if (self.color != firstCard.color && self.color != secondCard.color && firstCard.color != secondCard.color) {
        set++;
    }
    
    if (set == 4) {
        score += 3;
        self.cardLog = [NSString stringWithFormat:@"%@%@%@%@%@%@%@%@%@%@%@%@ is a matching set for ",self.number,self.symbol,self.shading,self.color,secondCard.number,secondCard.symbol,secondCard.shading,secondCard.color,firstCard.number,firstCard.symbol,firstCard.shading,firstCard.color];
    }
    else {
        self.cardLog = [NSString stringWithFormat:@"%@%@%@%@%@%@%@%@%@%@%@%@ is not a set ",self.number,self.symbol,self.shading,self.color,secondCard.number,secondCard.symbol,secondCard.shading,secondCard.color,firstCard.number,firstCard.symbol,firstCard.shading,firstCard.color];
    }
    
    return score;
}

-(void)setSymbol:(NSNumber*)symbol{
    if ([[SetCard elements] containsObject:symbol]) {
        _symbol = symbol;
    }
}

-(void)setColor:(NSNumber *)color{
    if ([[SetCard elements] containsObject:color]) {
        _color = color;
    }
}

-(void)setNumber:(NSNumber *)number{
    if ([[SetCard elements] containsObject:number]) {
        _number = number;
    }
}

-(void)setShading:(NSNumber *)shading{
    if ([[SetCard elements] containsObject:shading]) {
        _shading = shading;
    }
}
@end
