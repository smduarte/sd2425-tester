package fctreddit.impl.grpc.generated_java;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.62.2)",
    comments = "Source: src/fctreddit/impl/api/grpc/UsersAdmin.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class UsersAdminGrpc {

  private UsersAdminGrpc() {}

  public static final java.lang.String SERVICE_NAME = "UsersAdmin";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<fctreddit.impl.grpc.generated_java.UsersAdminProtoBuf.CheckUserArgs,
      com.google.protobuf.Empty> getCheckUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "checkUser",
      requestType = fctreddit.impl.grpc.generated_java.UsersAdminProtoBuf.CheckUserArgs.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<fctreddit.impl.grpc.generated_java.UsersAdminProtoBuf.CheckUserArgs,
      com.google.protobuf.Empty> getCheckUserMethod() {
    io.grpc.MethodDescriptor<fctreddit.impl.grpc.generated_java.UsersAdminProtoBuf.CheckUserArgs, com.google.protobuf.Empty> getCheckUserMethod;
    if ((getCheckUserMethod = UsersAdminGrpc.getCheckUserMethod) == null) {
      synchronized (UsersAdminGrpc.class) {
        if ((getCheckUserMethod = UsersAdminGrpc.getCheckUserMethod) == null) {
          UsersAdminGrpc.getCheckUserMethod = getCheckUserMethod =
              io.grpc.MethodDescriptor.<fctreddit.impl.grpc.generated_java.UsersAdminProtoBuf.CheckUserArgs, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "checkUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  fctreddit.impl.grpc.generated_java.UsersAdminProtoBuf.CheckUserArgs.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new UsersAdminMethodDescriptorSupplier("checkUser"))
              .build();
        }
      }
    }
    return getCheckUserMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UsersAdminStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UsersAdminStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UsersAdminStub>() {
        @java.lang.Override
        public UsersAdminStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UsersAdminStub(channel, callOptions);
        }
      };
    return UsersAdminStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UsersAdminBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UsersAdminBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UsersAdminBlockingStub>() {
        @java.lang.Override
        public UsersAdminBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UsersAdminBlockingStub(channel, callOptions);
        }
      };
    return UsersAdminBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UsersAdminFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UsersAdminFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UsersAdminFutureStub>() {
        @java.lang.Override
        public UsersAdminFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UsersAdminFutureStub(channel, callOptions);
        }
      };
    return UsersAdminFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void checkUser(fctreddit.impl.grpc.generated_java.UsersAdminProtoBuf.CheckUserArgs request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckUserMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service UsersAdmin.
   */
  public static abstract class UsersAdminImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return UsersAdminGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service UsersAdmin.
   */
  public static final class UsersAdminStub
      extends io.grpc.stub.AbstractAsyncStub<UsersAdminStub> {
    private UsersAdminStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UsersAdminStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UsersAdminStub(channel, callOptions);
    }

    /**
     */
    public void checkUser(fctreddit.impl.grpc.generated_java.UsersAdminProtoBuf.CheckUserArgs request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckUserMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service UsersAdmin.
   */
  public static final class UsersAdminBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<UsersAdminBlockingStub> {
    private UsersAdminBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UsersAdminBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UsersAdminBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.google.protobuf.Empty checkUser(fctreddit.impl.grpc.generated_java.UsersAdminProtoBuf.CheckUserArgs request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckUserMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service UsersAdmin.
   */
  public static final class UsersAdminFutureStub
      extends io.grpc.stub.AbstractFutureStub<UsersAdminFutureStub> {
    private UsersAdminFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UsersAdminFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UsersAdminFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> checkUser(
        fctreddit.impl.grpc.generated_java.UsersAdminProtoBuf.CheckUserArgs request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckUserMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CHECK_USER = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CHECK_USER:
          serviceImpl.checkUser((fctreddit.impl.grpc.generated_java.UsersAdminProtoBuf.CheckUserArgs) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getCheckUserMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              fctreddit.impl.grpc.generated_java.UsersAdminProtoBuf.CheckUserArgs,
              com.google.protobuf.Empty>(
                service, METHODID_CHECK_USER)))
        .build();
  }

  private static abstract class UsersAdminBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UsersAdminBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return fctreddit.impl.grpc.generated_java.UsersAdminProtoBuf.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("UsersAdmin");
    }
  }

  private static final class UsersAdminFileDescriptorSupplier
      extends UsersAdminBaseDescriptorSupplier {
    UsersAdminFileDescriptorSupplier() {}
  }

  private static final class UsersAdminMethodDescriptorSupplier
      extends UsersAdminBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    UsersAdminMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (UsersAdminGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UsersAdminFileDescriptorSupplier())
              .addMethod(getCheckUserMethod())
              .build();
        }
      }
    }
    return result;
  }
}
