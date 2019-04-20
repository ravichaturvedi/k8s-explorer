# README

## Build the code
  
    mvn clean package
  

## Build the docker image

    docker build -t k8s-explorer .
    

## Check Kubernetes Cluster
    
    // Checking components status
    kubectl get cs 
    
    // Checking for available nodes
    kubectl get nodes
    

## Access the host running the docker (in case of docker-for-mac Kubernetes)
    
    docker run -it --rm --privileged --pid=host justincormack/nsenter1
    

## Create any object using 

    kubectl create -f k8s/<path to yaml>
    

## Create a bastion host in the cluster (to check the cluster internal state)

    kubectl create -f k8s/bastion.yaml
    

## Shell access to the running pod

    kubectl exec -it <POD-NAME> <sh|bash>
    
    
## Kubernetes Object Spec Template

    Metadata
    	- name
    	- labels
    Spec:
        -
    Status
        -