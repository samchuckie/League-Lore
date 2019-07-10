const express = require('express')
const key = require('./key')
const path = require('path');
const app = express();
const port = 8080;
let baseurl = new URL('https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/');
baseurl.pathname ='charliesdemon';
baseurl.searchParams.append("api_key",key.key);
console.log(baseurl.toJSON());