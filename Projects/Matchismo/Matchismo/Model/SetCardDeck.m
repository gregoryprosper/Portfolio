//
//  SetCardDeck.m
//  Matchismo
//
//  Created by Gregory Prosper on 8/14/14.
//  Copyright (c) 2014 CS193p. All rights reserved.
//

#import "SetCardDeck.h"
#import "SetCard.h"

@implementation SetCardDeck

-(instancetype)init{
    self = [super init];
    
    if (self) {
        for (NSNumber *number in [SetCard elements]) {
            for (NSNumber *symbol in [SetCard elements]) {
                for (NSNumber *shading in [SetCard elements]) {
                    for (NSNumber *color in [SetCard elements]) {
                        SetCard *card = [[SetCard alloc]init];
                        card.number = number;
                        card.symbol = symbol;
                        card.shading = shading;
                        card.color = color;
                        [self addCard:card];
                    }
                }
            }
        }
    }
    
    return self;
}
@end
