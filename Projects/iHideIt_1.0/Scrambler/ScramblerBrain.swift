//
//  ScramblerBrain.swift
//  Scrambler
//
//  Created by Gregory Prosper on 6/28/15.
//  Copyright (c) 2015 Prosper Inc. All rights reserved.
//

import UIKit

class ScramblerBrain: NSObject {
    
    class func encryptString(estring: String, key: String) -> String {
        return FBEncryptorAES.encryptBase64String(estring, keyString: key, separateLines: false)
    }
    
    class func decryptData(estring: String, key: String) -> String? {
       return FBEncryptorAES.decryptBase64String(estring, keyString: key)
    }
}
