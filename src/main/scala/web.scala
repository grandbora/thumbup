
import com.twitter.finagle.{Service, SimpleFilter}
import org.jboss.netty.handler.codec.http._
import org.jboss.netty.handler.codec.http.HttpResponseStatus._
import org.jboss.netty.handler.codec.http.HttpVersion.HTTP_1_1
import org.jboss.netty.buffer.ChannelBuffers.copiedBuffer
// import com.twitter.io.Charsets.Utf8
import com.twitter.util.Future
import java.net.InetSocketAddress
import com.twitter.finagle.builder.{Server, ServerBuilder}
import com.twitter.finagle.http.Http

/**
 * This example demonstrates a sophisticated HTTP server that handles exceptions
 * and performs authorization via a shared secret. The exception handling and
 * authorization code are written as Filters, thus isolating these aspects from
 * the main service (here called "Respond") for better code organization.
 */
object Web {

  /**
   * The service itself. Simply echos back "hello world"
   */
  class Respond extends Service[HttpRequest, HttpResponse] {
    def apply(request: HttpRequest) = {
      val response = new DefaultHttpResponse(HTTP_1_1, LENGTH_REQUIRED)
      // response.setContent(copiedBuffer("hello world", Utf8))
      // response.setContentString("sdadas")
      Future.value(response)
    }
  }

  def main(args: Array[String]) {

    val respond = new Respond

    // compose the Filters and Service together:
    val myService: Service[HttpRequest, HttpResponse] = respond

    val server: Server = ServerBuilder()
      .codec(Http())
      .bindTo(new InetSocketAddress(8080))
      .name("httpserver")
      .build(myService)
  }
}
