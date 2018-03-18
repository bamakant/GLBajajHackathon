const express=require('express');
const app=express();
const mongoose=require('mongoose');
var bodyParser = require('body-parser')

//bodyparser config

// parse application/x-www-form-urlencoded
app.use(bodyParser.urlencoded({ extended: false }))

// parse application/json
app.use(bodyParser.json())

///connect to database
mongoose.connect('mongodb://time2hack:time2hack@ds117489.mlab.com:17489/time2hack',function(){
  console.log('Database conencted !');
});

//load the models

const User=require('./models/user');
const Item=require('./models/item');

//listen to the app

app.get('/insertuser',(req,res)=>{
  var newUser=new User({
      name:'Ashutosh Dwivedi',
      email:'webdev.ashutosh@gmail.com',
      password:'ashu@123',
      username:'kindacoder',
      mobile:'9560386672',
      address:'Galgotia college of engineering & Technology',
      fmhpoints:50,
    }).save(function(data,err){
      if(err){
        console.log(err);
      }
      else{
        console.log('Added in database');
        console.log(data);
      }
    })
  res.send('working fine bro !');
})

app.get('/getuser',function(req,res){
  User.find()
  .then(function(data){
    res.json(data);
  })
})


app.get('/signup/:name/:email/:mobile/:address/:username/:password/:referralcode',function(req,res){
const referralcode=req.params.referralcode;
const name=req.params.name;
const username=req.params.username;
const email=req.params.email;
const password=req.params.password;
const mobile=req.params.mobile;
const address=req.params.address;
var fmhpoints;
if(referralcode!='0'){
  fmhpoints=50;

}
else{
  fmhpoints=20;
}

///check which person referral code is this code;

// user.username==referralcode{
//   ///uska badha do!;
// }




var newUser=new User({
    name:name,
    email:email,
    password:password,
    username:username,
    mobile:mobile,
    address:address,
    referralcode:referralcode,
    fmhpoints:fmhpoints
  }).save(function(err,data){
    if(err){
      console.log(err);
      res.json({
        message:'error'
      })

    }
    else{
      console.log('user Added into database');
      console.log(data);
      res.json({
        message:'success'
      })
    }
  })



})




app.get('/login/:username/:password',function(req,res){
  //check if the user with this username alrerady exists
  var username=req.params.username;
  var password=req.params.password;
  User.findOne({username:username},function(err,data){
if(!data){
  res.json({
    message:'No user exists'
  });
}
else{
  ///check the password
  if(data.password==password){
    res.json({
      message:'SignIn successful',
      userid:data.id
    })
  }
  else{
    res.json({
      message:'Your password is Wrong'
    })
  }
  console.log(data);
}
  })
})


app.get('/item/insert/:itemName/:itemQuantity/:lng/:lat/:userid/:address',function(req,res){
  var newItem=new Item({
    itemName:req.params.itemName,
    itemQuantity:req.params.itemQuantity,
    lng:req.params.lng,
    lat:req.params.lat,
    userid:req.params.userid,
    address:req.params.address,

  }).save(function(err,data){
    if(err){
      console.log(err);
    }
    else{
      console.log('data saved into database');
      console.log(data);
      res.json(data);
    }
  })
})


///get the item

app.get('/getitem',function(req,res){
  Item.find({},function(err,data){
    if(err){
      console.log('error while fetching data');
      console.log(err);
    }
    else{
      console.log('data retrieved from database ');
      console.log(data);
      res.json(data);
    }
  })
})




app.listen('3000',()=>{
  console.log('server started at port 3000');
})
