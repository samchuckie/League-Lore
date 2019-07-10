const express = require('express')
const key = require('./key')
const path = require('path');
const app = express();
const port = 8080;
const https = require('https');


let profileicon = path.join(__dirname, 'dragontail-9.6.1','9.6.1','img','profileicon');
let loading = path.join(__dirname, 'dragontail-9.6.1','img','champion','loading');
let splash = path.join(__dirname, 'dragontail-9.6.1','img','champion','splash');
let passive = path.join(__dirname, 'dragontail-9.6.1','9.6.1','img','passive');
let spell = path.join(__dirname, 'dragontail-9.6.1','9.6.1','img','spell');
let champion = path.join(__dirname, 'dragontail-9.6.1','9.6.1','img','champion');
let map = path.join(__dirname ,'dragontail-9.6.1','9.6.1','img','map')
let baseurl = new URL('https://euw1.api.riotgames.com/lol/summoner/v4/summoners/by-name/');
let lol = 'lol';
let summoner ='summoner';
let v4 = 'v4';
let summoners = 'summoners';
let by_name = 'by-name';


app.use(express.static(profileicon));
app.use(express.static(loading));
app.use(express.static(splash));
app.use(express.static(passive));
app.use(express.static(spell));
app.use(express.static(champion));
app.use(express.static(map));
let data = '';

app.get('/img/profileicon/:imageid',(req,res) =>{
    let id = req.params.imageid;
    console.log(id);
    res.sendFile(path.join(profileicon,id));
})
app.get('/img/champion/loading/:imageid',(req,res) =>{
    let id = req.params.imageid;
    console.log(id);
    res.sendFile(path.join(loading,id));
})
app.get('/img/champion/splash/:imageid',(req,res) =>{
    let id = req.params.imageid;
    console.log(id);
    res.sendFile(path.join(splash,id));
})
app.get('/img/passive/:imageid',(req,res) =>{
    let id = req.params.imageid;
    console.log(id);
    res.sendFile(path.join(passive,id));
})
app.get('/img/spell/:imageid',(req,res) =>{
    let id = req.params.imageid;
    console.log(id);
    res.sendFile(path.join(spell,id));
})
app.get('/img/champion/:imageid',(req,res) =>{
    let id = req.params.imageid;
    console.log(id);
    res.sendFile(path.join(champion,id));
})
app.get('/:summonername',(req,res)=>{
    let summonername =req.params.summonername;
    let completebasepath =baseurl.pathname + summonername;
    console.log("Pathname is " + completebasepath);
    baseurl.pathname = completebasepath;
    baseurl.searchParams.append("api_key",key.key);

    console.log("Href is " + baseurl.href);
    https.get(baseurl.href, (resp) => {

  // A chunk of data has been recieved.
  resp.on('data', (chunk) => {
    data += chunk;
  });

  // The whole response has been received. Print out the result.
  resp.on('end', () => {
	charlie = JSON.parse(data);
	let newer =  JSON.stringify(data);
    res.send(charlie)

  });
})
})
app.get('/', (req,resp) =>{
    resp.sendFile(path.join(map ,'map11.png'));
})

app.listen(port ,()=>{
    console.log(`Server is live on port ${port}`);
})