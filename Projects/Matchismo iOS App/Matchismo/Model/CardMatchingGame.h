//
//  CardMatchingGame.h
//  Matchismo
//
//  Created by Gregory Prosper on 8/9/14.
//  Copyright (c) 2014 CS193p. All rights reserved.
//

#import <Foundation/Foundation.h>
@class Deck;
@class Card;

@interface CardMatchingGame : NSObject
@property (nonatomic,readonly) NSUInteger score;
@property (nonatomic,strong) NSMutableArray *log;
@property (nonatomic) NSUInteger gameMode;
@property (nonatomic) NSUInteger cardsSelected;
//Designated Initializer
-(instancetype)initWithCardCount:(NSUInteger)count
                       usingDeck:(Deck*)deck;

-(void)chooseCardAtIndex:(NSUInteger)index;
-(Card*)cardAtIndex:(NSUInteger)index;
@end
