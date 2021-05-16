const { CleanWebpackPlugin } = require("clean-webpack-plugin");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const path = require("path");
const webpack = require("webpack");

module.exports = {
  entry: {
    login: "./js/login/login_index.js",
    register: "./js/register/register_index.js",
    entries: "./js/entries/entries_index.js"
  },
  plugins: [
    //For Development, create the html file automatically with the bundle.
    new webpack.IgnorePlugin(/^\.\/locale$/, /moment$/),
    new CleanWebpackPlugin(),
  ],
  output: {
    filename: "[name].js",
    path: path.resolve(__dirname, "dist"),
  },
  module: {
    rules: [
      {
        test: /\.(js|jsx)$/,
        loader: "babel-loader",
        exclude: /node_modules/,
        query: {
          presets: ["@babel/preset-env"],
        },
      },
      {
    test: /\.css$/,
    use: ["style-loader", "css-loader"]
},
    ],
  },
 
};
