package fctreddit.impl.grpc.servers;

import java.util.Collection;
import java.util.function.Function;

import fctreddit.api.java.Result;
import io.grpc.BindableService;
import io.grpc.stub.StreamObserver;

public abstract class AbstractGrpcStub implements BindableService {

	protected static Throwable errorCodeToStatus( Result.ErrorCode error ) {
    	var status =  switch( error) {
    	case NOT_FOUND -> io.grpc.Status.NOT_FOUND; 
    	case CONFLICT -> io.grpc.Status.ALREADY_EXISTS;
    	case FORBIDDEN -> io.grpc.Status.PERMISSION_DENIED;
    	case NOT_IMPLEMENTED -> io.grpc.Status.UNIMPLEMENTED;
    	case BAD_REQUEST -> io.grpc.Status.INVALID_ARGUMENT;
    	default -> io.grpc.Status.INTERNAL;
    	};
    	
    	return status.asException();
    }
	
	protected static <Q, T> void toGrpcResult( StreamObserver<T> responseObserver, Result<Q> r, Function<Q, T> f) {
		if( r.isOK() ) {
			responseObserver.onNext( f.apply( r.value()) );
			responseObserver.onCompleted();
		} else responseObserver.onError( errorCodeToStatus(r.error()));
		
	}
	
	protected static <Q, T> void toGrpcResultCollection( StreamObserver<T> responseObserver, Result<? extends Collection<Q>> r, Function<Q, T> f) {
		if( r.isOK() ) {			
			r.value().forEach( val -> {
				responseObserver.onNext( f.apply(val));
			});
			responseObserver.onCompleted();
		} else responseObserver.onError( errorCodeToStatus(r.error()));
	}
}
