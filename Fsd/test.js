var http = require('http')
var fs = require('fs')
var querystring = require('querystring')
var port = 3002;

http.createServer(function (req, res) {
    let body = '';
    if (req.method === 'GET') {
        fs.readFile('index.html', function (err, data) {
            if (err) {
                res.writeHead(404, { 'Content-Type': 'text/html' });
                res.write("File Not Found");
                return res.end();
            }
            res.writeHead(200, { 'Content-Type': 'text/html' });
            res.write(data);
            return res.end();
        });
    } else if (req.method === 'POST') {
        req.on('data', chunk => {
            body += chunk.toString();
        });

        req.on('end', () => {
            const postData = querystring.parse(body);
            const file_1 = postData['file1'];
            const file_2 = postData['file2'];

            fs.readFile(file_1, 'utf-8', (err, data) => {
                if (err) {
                    res.writeHead(404, { 'Content-Type': 'text/html' });
                    res.write("File Not Found");
                    return res.end();
                }

                fs.appendFile(file_2, data, 'utf-8', err => {
                    if (err) {
                        res.writeHead(500, { 'Content-Type': 'text/html' });
                        res.write("Error Appending File");
                        return res.end();
                    }
                    res.writeHead(200, { 'Content-Type': 'text/html' });
                    res.write("File Appended");
                    return res.end();
                });
            });
        });
    }
}).listen(port, () => {
    console.log(`Server running at port ${port}`);
});