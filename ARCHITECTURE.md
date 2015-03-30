# Deployment

Deployment should be simple and depend only on widely available resources.
Heroku is the current deployment model.  The free tier provides a good place
for organizations to deploy their own copy of this application.

# Operating Cost

The goal of this project is to operate using entirely free-tier resources.
This goal is to support free and open source projects.

Technologies selected based on the value they provide at the free-tier:

 * [Heroku](http://heroku.com/) Free 750-Dyno hours per month.  In a 31 day
   month, 744 hours will be consumed by the `web.1` dyno process.  This leaves
   6 hours remaining for a `worker.1` dyno process.  These 6 free hours of
   worker process time need to be carefully managed, however.  A persistent
   worker process cannot be run for free.
 * [cloudamqp](https://addons.heroku.com/cloudamqp) allows an unlimited number
   of messages with 3 concurrent connections.  The CLA application will have
   messages proportional to the number of contributions but should require only
   2 concurrent AMQP connections.  Three concurrent connections will be good
   for operating on the queue during production use.

# Language

Clojure.  Because, why not?

# Authentication

The existing CLA uses Github OAuth for user authentication.  This CLA
application will preserve this behavior.

# Worker Model

Worker processes should only run when there is work to be done.  This follows
the workless process model in Ruby where worker processes execute in the extra
6 hours of the 750 free process hours per month.
