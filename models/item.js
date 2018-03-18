const mongoose=require('mongoose');
const Schema=mongoose.Schema;


///creating item Schema

const itemSchema=new Schema({
  itemName:{
    type:String,
    required: true
  },
  itemQuantity:{
    type:String,
    required:true
  },
  date:{
    type: Date,
    default: Date()
   },
   lng:{
     type:Number,
     required:true
  },
  lat:{
    type:Number,
    required:true
  },
  userid:{
    type:String,
    required:true

  },
  address:{
    type:String,
    required:true
  }
   // user: {
   //   type: Schema.Types.ObjectId,
   //    ref: 'users'
   //   }

})

const Item=mongoose.model('item',itemSchema);
module.exports=Item;
