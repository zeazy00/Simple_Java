const path = require('path');

module.exports = {
    entry: './target/JS/index.js',
    module: {
        rules: [
            {test: /\.svg$/, use: 'svg-inline-loader'},
            {test: /\.svg$/, use: ['style-loader', 'css-loader']},
            {test: /\.(js)$/, use: 'babel-loader'},
        ]
    },
    output: {
        path: path.resolve(__dirname,'target/JS'),
        filename: "index_bundle.js"
    }
}