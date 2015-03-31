# Developer Resources

These documentation resources were used during development of this application.

## Documentation

 * [Database Connection Pooling with Clojure][pooling] Useful for how to handle
   automatic migration, parsing of the `DATABASE_URL` into a JDBC string which
   is required by the ragtime database migration lein task.
 * [Heroku Language Support][heroku-language-support] Useful for how to run
   Clojure effectively in Heroku.
 * [Heroku PostgreSQL Add-On][heroku-postgresql] Useful for how to add a
   PostgreSQL database to a Heroku app.

## Libraries

 * [heroku-database-url-to-jdbc][heroku-database-url-to-jdbc] Useful for
   connecting your JDBC-using, Heroku-deployed Clojure application to Heroku's
   postgres service.

## Running Locally

Make sure you have Clojure installed.  Also, install the [Heroku
Toolbelt](https://toolbelt.heroku.com/).

```sh
$ git clone https://github.com/heroku/clojure-getting-started.git
$ cd clojure-getting-started
$ lein repl
user=> (require 'clojure-getting-started.web)
user=>(def server (clojure-getting-started.web/-main))
```

Your app should now be running on [localhost:5000](http://localhost:5000/).

### Database Setup

Here's how I got things going on Mac OS X.  All ran as my normal user account,
not as root.

    brew install postgres

Initialize the databases:

    initdb /usr/local/var/postgres -E utf8

Configure to run at login:

    ln -sfv /usr/local/opt/postgresql/*.plist ~/Library/LaunchAgents

Start the database now:

    launchctl load ~/Library/LaunchAgents/homebrew.mxcl.postgresql.plist

Create the DB for the application:

    createdb mydb

Create the role for the application:

    psql claapp --command="CREATE USER claapp WITH PASSWORD '${DATABASE_PASSWORD:-claapp}';"

With this configuration, the ragtime database connection string specified in
project.clj should connect to the local postgres database.

## Deploying to Heroku

```sh
$ heroku create
$ heroku addons:add heroku-postgresql:hobby-dev
$ git push heroku master
$ heroku open
```

### Database Migration

Make sure you have a database configured locally, or a database added to
heroku, or your PaaS of choice.  Then run:

    lein ragtime migrate

For example, on Heroku:

    heroku run lein ragtime migrate

Database migration requires the `DATABASE\_URL` environment variable, otherwise
it defaults to connecting to
`"jdbc:postgresql://localhost/claapp?user=claapp&password=claapp"`.

## Troubleshooting

If you're unable to run `heroku run lein ragtime migrate`, try `heroku run
'lein do clean; lein ragtime migrate;'` as a workaround.

[pooling]: https://devcenter.heroku.com/articles/database-connection-pooling-with-clojure#deploying-the-app-to-heroku
[heroku-language-support]: https://devcenter.heroku.com/categories/language-support#clojure
[heroku-postgresql]: https://devcenter.heroku.com/articles/heroku-postgresql#provisioning-the-add-on
[heroku-database-url-to-jdbc]: https://github.com/thoughtbot/heroku-database-url-to-jdbc
