const mysql = require('mysql');

const con = mysql.createConnection({
    host:'localhost',
    user:'root',
    password:'Vishal@22'
});

con.connect((err)=>{
    if(err) throw err;
    console.log("Connected to mysql");
});

const createdb = "create database customer;";

con.query(createdb,(err)=>{
    if(err) throw err;
    console.log("Created Customer database succesfuly.");
});

con.changeUser({
    database:'customer'
});

const createtb = "create table cust_details(cus_id int primary key, cus_name varchar(40));";

con.query(createtb, (err)=>{
    if(err) throw err;
    console.log("Table created successfuly.");
})

const inserttb = [
    [1,"Vishal"],
    [2,"Tejas"],
    [3,"Shivam"],
    [4,"Sourabh"],
    [5,"Riya"]
];

const insertq = "insert into cust_details values ?;";

con.query(insertq,[inserttb],(err)=>{
    if(!err) console.log("Values inserted succesfuly");
});

const showdet = "select * from cust_details;"

con.query(showdet,(err,result)=>{
    if(!err) console.log(result);
});

const showdetcus = "select * from cust_details where cus_name like 'S%';";

con.query(showdetcus,(err,result)=>{
    if(err) throw err;
    console.log("Showing customised output:");
    result.forEach(ele=>{
        console.log(`Customer Id : ${ele.cus_id} Customer name : ${ele.cus_name}`);
    });
});

con.end();