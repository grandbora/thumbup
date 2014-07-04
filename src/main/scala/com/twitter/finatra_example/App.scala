package com.twitter.finatra_example

import com.twitter.finatra._
import com.twitter.finatra.ContentType._
import scalikejdbc._






object App extends FinatraServer {


  class ExampleApp extends Controller {

    // smth random
    get("/") { request =>
      val entities: List[Map[String, Any]] = sql"select * from members".map(_.toMap).list.apply()
      render.plain(entities.head("NAME").toString).toFuture
    }

    get("/gif") { request =>
      render.static("/dealwithit.gif").toFuture
    }

    get("/redirect") { request =>
      redirect("http://thumbup.herokuapp.com/gif", permanent = true).toFuture
    }


  }



  System.setProperty("com.twitter.finatra.config.port", ":" + System.getenv("PORT"))

  Class.forName("org.h2.Driver")
  ConnectionPool.singleton("jdbc:h2:mem:hello", "user", "pass")

  implicit val session = AutoSession



  sql"""
create table members (
  id serial not null primary key,
  name varchar(64),
  created_at timestamp not null
)
""".execute.apply()


Seq("Alice", "Bob", "Chris") foreach { name =>
  sql"insert into members (name, created_at) values (${name}, current_timestamp)".update.apply()
}



  register(new ExampleApp())
}
