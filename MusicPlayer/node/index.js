const http = require('http');

http.createServer((req, res ) => {
    res.write("harsh");
    res.end();
}).listen(8080);