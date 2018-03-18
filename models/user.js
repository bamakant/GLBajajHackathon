const mongoose=require('mongoose');
const Schema=mongoose.Schema;

//creating user Schema

const userSchema=new Schema({
  name:{
    type:String,
    required: true
  },
  email:{
    type: String,
    required: true
  },
  username:{
    type:String,
    required:true
  },
  password: {
    type:String,
    required:true
  },
  mobile:{
    type:Number,
    required:true
  },
  address:{
    type:String,
    required:true
  },
  referralcode:{
    type:String
  },
  fmhpoints:{
    type:Number,
    required:true
  }
})

const User=mongoose.model('user',userSchema);
module.exports=User;
