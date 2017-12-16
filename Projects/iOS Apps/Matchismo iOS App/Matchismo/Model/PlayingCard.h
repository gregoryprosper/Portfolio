//
//  PlayingCard.h
//  Matchismo
//
//  Created by Gregory Prosper on 8/7/14.
//  Copyright (c) 2014 CS193p. All rights reserved.
//

#import "Card.h"

@interface PlayingCard : Card
@property (strong, nonatomic) NSString *suit;
@property (nonatomic) NSInteger rank;

+(NSArray*)validSuits;
+(NSUInteger)maxRank;

@end
