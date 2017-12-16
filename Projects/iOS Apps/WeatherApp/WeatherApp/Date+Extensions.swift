//
//  Date+Extensions.swift
//  WeatherApp
//
//  Created by Gregory Prosper on 12/15/17.
//  Copyright © 2017 Gregory Prosper. All rights reserved.
//

import Foundation

extension Date {
    func dayOfTheWeek() -> String {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "EEEE"
        return dateFormatter.string(from: self)
    }
}
