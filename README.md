# Storage Data Service (REST)

The **storage data service** is the service that redirects requests from the services above to the right data-related service below. It represents a sort of aggregator that provides a uniform REST interface to the high level services.

| resource | link |
|----------|------|
| API documentation | http://docs.storagedataservice.apiary.io/ |
| Heroku base URL | https://storage-data-service-ar.herokuapp.com/rest/ |

### How to run it
Since the server is already deployed on Heroku, it is only needed to go to the Heroku base URL. However, you can also deploy again the server on Heroku via ant.

**Optional**: If you want to try the server locally, you can follow the steps below:
* **Clone** the repo: `git clone https://github.com/service-engineering-final-project/storage_data_service.git`;
* **Navigate** into the project folder: `cd storage_data_service`;
* **Install** the packages needed: `ant install`;
* **Run** the server using ant: `ant execute.server`.