import 'package:flutter/material.dart';

void main()=>runApp(MyApp());

class MyApp extends StatelessWidget{

  @override
  Widget build(BuildContext context) {

    return MaterialApp(
      title: "listview",
      home:HomePage(),
    );
  }
}

class HomePage extends StatefulWidget{
  @override
  State<StatefulWidget> createState() {
    return ListViewState();
  }
}

class ListViewState extends State<HomePage>{


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title:Text('listView')),
      body:ListView(
        children: <Widget>[
          getItem('xxx'),
          Divider(),
          getItem('xxx'),
          Divider()
        ],
      )
    );
  }

  Widget getItem(String title){
    return Container(
      width: 160.0,
      child: ListTile(
        leading: Icon(Icons.map),
        title: Text(title),
        trailing: Icon(Icons.add),
        subtitle: Text(title),
        onTap: (){
          print("我点击了");
          Navigator.push(context, new MaterialPageRoute(builder: (context) => SecondScreen()));
        },
      ),
    );
  }
}

class SecondScreen extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return new MaterialApp();
  }
}