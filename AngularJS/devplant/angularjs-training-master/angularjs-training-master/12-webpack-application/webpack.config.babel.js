import HtmlWebpackPlugin from "html-webpack-plugin";
import path from "path";


export default {

    entry: './app/app.js',

    output: {
        path: path.resolve(__dirname, 'dist'),
        filename: "bundle.js"
    },

    devServer: {
        historyApiFallback: true
    },

    plugins: [new HtmlWebpackPlugin({template: './app/index.html'})],

    module: {
        loaders: [
            {
                test: /\.js$/, loader: 'babel-loader', exclude: /node_modules/, query: {
                presets: ['es2015']
            }
            },
            {test: /\.html$/, loader: 'raw-loader', exclude: /node_modules/},
            {test: /\.css$/, loader: 'style-loader!css-loader'}
        ]
    }
};
