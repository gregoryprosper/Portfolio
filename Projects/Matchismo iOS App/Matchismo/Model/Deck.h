//
//  Deck.h
//  Matchismo
//
//  Created by Gregory Prosper on 8/7/14.
//  Copyright (c) 2014 CS193p. All rights reserved.
//

#import <Foundation/Foundation.h>
@class Card;

@interface Deck : NSObject
@property (strong,nonatomic) NSMutableArray *cards;

-(void)addCard:(Card*)card atTop:(BOOL)atTop;
-(void)addCard:(Card*)card;

-(Card*)drawRandomCard;

@end
