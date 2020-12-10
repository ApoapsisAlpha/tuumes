const PRIVATE_API = "http://localhost:8080/api";
const userType = {
    ATTENDEE: 'ATTENDEE',
    VIP: 'VIP',
    SPEAKER: 'SPEAKER',
    ORGANIZER: 'ORGANIZER',
}

function validate(form) {
    return document.getElementById($(form).attr('id')).checkValidity();
}

$.postJSON = function(url, data, callback) {
    return jQuery.ajax({
    headers: { 
        'Accept': 'application/json',
        'Content-Type': 'application/json' 
    },
    'type': 'POST',
    'url': PRIVATE_API + url,
    'data': JSON.stringify(data),
    'dataType': 'json',
    'success': callback
    });
};