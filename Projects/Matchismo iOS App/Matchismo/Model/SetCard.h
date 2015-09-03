//
//  SetCard.h
//  Matchismo
//
//  Created by Gregory Prosper on 8/14/14.
//  Copyright (c) 2014 CS193p. All rights reserved.
//

#import "Card.h"

@interface SetCard : Card
@property (nonatomic) NSNumber *symbol;
@property (nonatomic) NSNumber *color;
@property (nonatomic) NSNumber *number;
@property (nonatomic) NSNumber *shading;

+(NSArray*)elements;

@end
