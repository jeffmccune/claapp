# Migration from Ruby CLA

This is the migration plan from the Ruby CLA application:

Migrate the database.  Use [schema.rb][schema] as a source of information.

Implement Github Authentication.

Implement the sign workflow.

Implement a webhook event receiver.

 * Stores the payload for asynchronous processing.  Is it best to store this in
   the AMQP message.  Should there be no state whatsoever written to the
   database?  The design of the hand-off of the data is something that still
   isn't clear to me.

Implement a pull-request responder.

 * Triggered when a worker processes a pull-request event.
 * Responds to the pull request by updating the pull-request status API.
   "Waiting to hear about..."

Upload CLA Documents

The CLA needs a way to upload new versions of the agreement being signed.

Signature Workflow

Email

The CLA needs to send email when processing the signature workflow.

[schema]: https://github.com/puppetlabs/cla/blob/f5ee7c9/db/schema.rb
