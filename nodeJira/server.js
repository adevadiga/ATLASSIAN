var express = require('express');
var app = express();

app.get('/rest/api/2/search', function (req, res) {
    const data = [{
        "issueKey": "TEST-1",
        "fields": {
            "storyPoints": 1
        }
    },{
        "issueKey": "TEST-2",
        "fields": {
            "storyPoints": 2
        }
    }, {
        "issueKey": "TEST-3",
        "fields": {
            "storyPoints": 5
        }
    }]
    res.send(data);
});

var server = app.listen(5000, function () {
    console.log('Node server is running..');
});