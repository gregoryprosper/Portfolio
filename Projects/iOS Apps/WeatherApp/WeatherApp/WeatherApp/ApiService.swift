//
//  ApiService.swift
//  WeatherApp
//
//  Created by Gregory Prosper on 12/14/17.
//  Copyright © 2017 Gregory Prosper. All rights reserved.
//

import Foundation
import Alamofire

class ApiService {
    static let shared: ApiService = ApiService()
    
    fileprivate let appid = "d7325899b66308503e1c362e3fc17e63"
    fileprivate let baseUrl = "https://api.openweathermap.org/data/2.5"
    
    fileprivate init(){}
    
    func getCurrentWeather(location: String, onCompletion completed: @escaping (CurrentWeather?) -> ()) -> Void {
        guard let  q = location.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed) else { return }
        let url = "\(baseUrl)/weather?q=\(q)&appid=\(appid)"
        
        Alamofire.request(url).responseJSON { response in
            let result = response.result
            var currentWeather: CurrentWeather? = nil
            
            if let dict = result.value as? Dictionary<String, AnyObject> {
                currentWeather = CurrentWeather(weatherDict: dict)
            }
            completed(currentWeather)
        }
    }
    
    func getCurrentWeather(lat: Double, long: Double, onCompletion completed: @escaping (CurrentWeather?) -> ()) -> Void {
        let url = "\(baseUrl)/weather?lat=\(lat)&lon=\(long)&appid=\(appid)"
        
        Alamofire.request(url).responseJSON { response in
            let result = response.result
            var currentWeather: CurrentWeather? = nil
            
            if let dict = result.value as? Dictionary<String, AnyObject> {
                currentWeather = CurrentWeather(weatherDict: dict)
            }
            completed(currentWeather)
        }
    }
    
    func getForecast(lat: Double, long: Double, onCompletion completed: @escaping ([Forecast]) -> ()) -> Void {
        let url = "\(baseUrl)/forecast/daily?lat=\(lat)&lon=\(long)&cnt=10&appid=\(appid)"
        
        Alamofire.request(url).responseJSON { response in
            let result = response.result
            var forecasts = [Forecast]()
            
            if let dict = result.value as? Dictionary<String, AnyObject> {
                if let list = dict["list"] as? [Dictionary<String, AnyObject>] {
                    for obj in list {
                        let forecast = Forecast(weatherDict: obj)
                        forecasts.append(forecast)
                    }
                    forecasts.remove(at: 0)
                }
            }
            completed(forecasts)
        }
    }
}
