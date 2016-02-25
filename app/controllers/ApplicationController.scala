package controllers

import cats.std.all._
import cats.syntax.all._
import com.amazonaws.services.ec2.AmazonEC2Client
import com.amazonaws.services.ec2.model.DescribeInstancesRequest
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

import scala.collection.JavaConversions._

object ApplicationController extends Controller {

  def findSwarmManager(gitRepositoryName: String, gitHash: String) = Action {
    val client = new AmazonEC2Client()
    val privateIpAddress = client.describeInstances(new DescribeInstancesRequest()).getReservations.flatMap(_.getInstances).find { i =>
      i.getTags.exists { t => t.getKey === "GitRepositoryName" && t.getValue === gitRepositoryName } &&
        i.getTags.exists { t => t.getKey === "GitHash" && t.getValue === gitHash } &&
        i.getTags.exists { t => t.getKey === "SwarmRole" && t.getValue === "Manager" }
    }.map { i =>
      i.getPrivateIpAddress
    }
    Ok(Json.toJson(privateIpAddress))
  }
}