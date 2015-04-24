//
//  CardGameViewController.h
//  Matchismo
//
//  Created by Gregory Prosper on 8/7/14.
//  Copyright (c) 2014 CS193p. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Deck.h"

@interface CardGameViewController : UIViewController
-(Deck*)createDeck;
-(NSAttributedString*)titleForCard:(Card *)card;
-(UIImage*)backgroundImageForCard:(Card *)card;
-(void)updateUI;
-(void)updateLogLabel;
-(NSString*)convertToSymbols:(NSInteger)number :(NSInteger)symbol;
-(NSDictionary*)applyAttributes:(NSInteger)shading :(NSInteger)color;
@end
