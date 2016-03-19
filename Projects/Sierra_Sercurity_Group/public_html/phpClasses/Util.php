<?php

class Util {

    public static function getServiceType($service) {
        switch ($service) {
            case "structural":
                return 'Structural Security';
            case "event":
                return 'Event Security';
            case "personal":
                return 'Personal Security/Secure Transportation';
            case "other":
                return 'Other Needs';
            default :
                return 'None';
        }
    }

    public static function requiredFieldsSet($postArray) {
        if (isset($postArray["firstName"]) && isset($postArray["lastName"]) && isset($postArray["email"]) && isset($postArray["serviceType"]) 
                && isset($postArray["shouldBeArmed"]) && isset($postArray["fromDate"]) && isset($postArray["toDate"]) 
                && isset($postArray["description"]) && isset($postArray["fromTime"]) && isset($postArray["toTime"]) 
                && isset($postArray["location"])) {
            return true;
        } else {
            return false;
        }
    }

    public static function getEmailBody($postArray) {
        $firstName = $postArray["firstName"];
        $lastName = $postArray["lastName"];
        $email = $postArray["email"];
        $service = $postArray["serviceType"];
        $shouldBeArmed = $postArray["shouldBeArmed"];
        $fromDate = $postArray["fromDate"];
        $fromTime = $postArray["fromTime"];
        $toDate = $postArray["toDate"];
        $toTime = $postArray["toTime"];
        $description = $postArray["description"];
        $location = $postArray["location"];
        $phoneNumber = isset($postArray["phone"]) && !empty($postArray["phone"]) ? $postArray["phone"] : "Not Provided";

        $body = '<html><body>';
        $body .= '<table rules="all" style="border-color: #666; width: 100%;" cellpadding="10">';
        $body .= '<tr style="background: #eee;">
                    <td><strong>Name:</strong></td>
                    <td>' . $firstName . " " . $lastName . '</td>
                 </tr>
                 <tr>
                    <td><strong>Email:</strong></td>
                    <td>' . $email . '</td>
                 </tr>
                 <tr>
                    <td><strong>Phone Number:</strong></td>
                    <td>' . $phoneNumber . '</td>
                 </tr>
                 <tr>
                    <td><strong>Location:</strong></td>
                    <td>' . $location . '</td>
                 </tr>
                 <tr>
                    <td><strong>Service:</strong></td>
                    <td>' . Util::getServiceType($service) . '</td>
                 </tr>
                 <tr>
                    <td><strong>Should Be Armed:</strong></td>
                    <td>' . ucwords($shouldBeArmed) . '</td>
                 </tr>
                 <tr>
                    <td><strong>From Date:</strong></td>
                    <td>' . $fromDate . " " . $fromTime . '</td>
                 </tr>
                 <tr>
                    <td><strong>To Date:</strong></td>
                    <td>' . $toDate . " " . $toTime . '</td>
                 </tr>
                 <tr>
                    <td><strong>Description:</strong></td>
                    <td>' . $description . '</td>
                 </tr>';
        $body .= '</table>';
        $body .= '</body></html>';
        
        return $body;
    }

}

?>